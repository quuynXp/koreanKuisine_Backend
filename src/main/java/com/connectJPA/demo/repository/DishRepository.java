package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Dish;
import com.connectJPA.demo.entity.Drinks;
import com.connectJPA.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish , String>{
    @Query("SELECT d FROM Dish d WHERE " +
            "(:type IS NULL OR d.type = :type) AND " +
            "(:description IS NULL OR d.description LIKE CONCAT('%', :description, '%')) AND " +
            "(:minPrice IS NULL OR d.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR d.price <= :maxPrice) AND " +
            "(:minRating IS NULL OR d.rating >= :minRating) AND " +
            "(:maxRating IS NULL OR d.rating <= :maxRating)")
    List<Dish> findByFilters(@Param("type") String type,
                               @Param("description") String description,
                               @Param("minPrice") BigDecimal minPrice,
                               @Param("maxPrice") BigDecimal maxPrice,
                               @Param("minRating") Double minRating,
                               @Param("maxRating") Double maxRating);



    boolean existsByName(String name);
    Optional<Dish> findByName(String name);
    Optional<List<Dish>> findByType(String type);
    List<Dish> findByTypeIn(String type);
    Optional<List<Dish>> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);
    Optional<List<Dish>> findByRatingBetween(double ratingMin, double ratingMax);
    Optional<List<Dish>> findByNameContaining(String name);
    Optional<List<Dish>> findByDescriptionContaining(String basicInfo);

}
