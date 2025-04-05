package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.Subscriber;
import com.connectJPA.demo.repository.SubscriberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {

    JavaMailSender mailSender;
    SubscriberRepository subscriberRepository;

    @Value("${spring.mail.username}")
    String myEmail;

    public void sendSubscriptionConfirmation(String to) throws MessagingException {
        String subject = "✅ Bạn đã đăng ký nhận voucher thành công!";
        String content = """
            Korean Kuisine xin chào,
            
            Cảm ơn bạn đã đăng ký nhận voucher từ nhà hàng của chúng tôi. Chúng tôi sẽ gửi voucher mới nhất đến email của bạn ngay khi có chương trình ưu đãi.
            
            Chúc bạn một ngày tốt lành!
            """;

        sendEmail(to, subject, content);
    }

    public void sendVoucherToSubscribers(String voucherCode) throws MessagingException {
        List<Subscriber> subscribers = subscriberRepository.findAll();

        for (Subscriber subscriber : subscribers) {
            String subject = "Korean Kuisine Voucher – Nhận ngay!";
            String content = """
                Xin chào,
                
                Chúng tôi vừa ra mắt voucher mới dành riêng cho bạn:
                Giảm giá 20%% cho đơn hàng đầu tiên!
                
                Sử dụng mã: 241224 khi đặt hàng.
                
                Hãy nhanh tay vì số lượng có hạn!
                """.formatted(voucherCode);

            sendEmail(subscriber.getEmail(), subject, content);
        }
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setFrom(myEmail);

        mailSender.send(message);
    }
}

