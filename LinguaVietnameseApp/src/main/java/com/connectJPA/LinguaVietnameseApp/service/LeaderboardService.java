package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.LeaderboardEntryDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Leaderboard;
import com.connectJPA.LinguaVietnameseApp.entity.LeaderboardEntry;
import com.connectJPA.LinguaVietnameseApp.entity.User;
import com.connectJPA.LinguaVietnameseApp.repository.LeaderboardEntryRepository;
import com.connectJPA.LinguaVietnameseApp.repository.LeaderboardRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepo;
    private final LeaderboardEntryRepository entryRepo;
    private final UserRepository userRepo;

    @Cacheable(value = "leaderboardTop3", key = "#tab + ':' + #period")
    public List<LeaderboardEntryDTO> getTop3(String tab, String period) {
        Leaderboard lb = getCurrentLeaderboard(tab, period);
        return entryRepo.findTop3ByLeaderboardIdOrderByRankAsc(lb.getLeaderboardId())
                .stream().map(this::toDTO).toList();
    }

    @Cacheable(value = "leaderboardPaged", key = "#tab + ':' + #period + ':' + #page + ':' + #size")
    public Page<LeaderboardEntryDTO> getPaged(String tab, String period, int page, int size) {
        Leaderboard lb = getCurrentLeaderboard(tab, period);
        return entryRepo.findByLeaderboardIdOrderByRankAsc(lb.getLeaderboardId(), PageRequest.of(page, size))
                .map(this::toDTO);
    }

    @Cacheable(value = "leaderboardFocus", key = "#tab + ':' + #period + ':' + #userId")
    public LeaderboardEntryDTO getFocusUserEntry(String tab, String period, String userId) {
        Leaderboard lb = getCurrentLeaderboard(tab, period);
        return entryRepo.findByLeaderboardIdAndUserId(lb.getLeaderboardId(), userId)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found in leaderboard"));
    }

    @CacheEvict(value = { "leaderboardTop3", "leaderboardPaged", "leaderboardFocus" }, allEntries = true)
    public void snapshotLeaderboard(String tab, String period) {
        LocalDate today = LocalDate.now();

        if (leaderboardRepo.findByTabAndPeriodAndSnapshotDate(tab, period, today).isPresent()) return;

        Optional<Leaderboard> yesterdayOpt = leaderboardRepo.findTopByTabAndPeriodOrderBySnapshotDateDesc(tab, period);
        Map<String, Integer> oldRanks = new HashMap<>();
        if (yesterdayOpt.isPresent()) {
            for (LeaderboardEntry e : entryRepo.findByLeaderboardId(yesterdayOpt.get().getLeaderboardId())) {
                oldRanks.put(e.getUserId(), e.getRank());
            }
        }

        Leaderboard lb = Leaderboard.builder()
                .tab(tab)
                .period(period)
                .snapshotDate(today)
                .leaderboardEntryIds(new ArrayList<>())
                .build();

        lb = leaderboardRepo.save(lb);

        List<User> users = userRepo.findAll();

        Leaderboard savedLb = leaderboardRepo.save(lb);

        List<LeaderboardEntry> entries = users.stream()
                .map(u -> LeaderboardEntry.builder()
                        .leaderboardId(savedLb.getLeaderboardId())
                        .userId(u.getUserId())
                        .score(u.getScore())
                        .level(u.getLevel())
                        .streak(u.getStreak())
                        .rank(0)
                        .change(0)
                        .build())
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .collect(Collectors.toList());

        savedLb.setLeaderboardEntryIds(
                entries.stream().map(LeaderboardEntry::getLeaderboardEntryId).toList()
        );
        leaderboardRepo.save(savedLb);


        for (int i = 0; i < entries.size(); i++) {
            LeaderboardEntry entry = entries.get(i);
            int newRank = i + 1;
            entry.setRank(newRank);
            Integer oldRank = oldRanks.get(entry.getUserId());
            entry.setChange(oldRank != null ? oldRank - newRank : 0);
        }

        entryRepo.saveAll(entries);

        // Gán lại entryIds vào leaderboard
        List<String> entryIds = entries.stream()
                .map(LeaderboardEntry::getLeaderboardEntryId)
                .toList();
        lb.setLeaderboardEntryIds(entryIds);
        leaderboardRepo.save(lb);
    }



    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Ho_Chi_Minh")
    public void scheduledSnapshotAllTabs() {
        List<String> tabs = List.of("top100", "week", "events", "seasons", "friends", "couples");
        List<String> periods = List.of("all", "month", "week", "today");
        for (String tab : tabs) {
            for (String period : periods) {
                snapshotLeaderboard(tab, period);
            }
        }
    }

    private Leaderboard getCurrentLeaderboard(String tab, String period) {
        return leaderboardRepo.findByTabAndPeriodAndSnapshotDate(tab, period, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("No leaderboard snapshot for today"));
    }

    private LeaderboardEntryDTO toDTO(LeaderboardEntry e) {
        Optional<User> u = userRepo.findById(e.getUserId());
        return LeaderboardEntryDTO.builder()
                .userId(u.get()
                        .getUserId())
                .name(u.get()
                        .getFullname())
                .avatarUrl(u.get()
                        .getAvatarUrl())
                .country(u.get()
                        .getCountry())
                .score(e.getScore())
                .level(e.getLevel())
                .streak(e.getStreak())
                .rank(e.getRank())
                .change(e.getChange())
                .build();
    }
}
