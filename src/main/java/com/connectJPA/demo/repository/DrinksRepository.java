package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Dish;
import com.connectJPA.demo.entity.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DrinksRepository extends JpaRepository<Drinks, String>{
    @Query("SELECT d FROM Drinks d WHERE " +
            "(:type IS NULL OR d.type = :type) AND " +
            "(:description IS NULL OR d.description LIKE CONCAT('%', :description, '%')) AND " +
            "(:minPrice IS NULL OR d.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR d.price <= :maxPrice) AND " +
            "(:minRating IS NULL OR d.rating >= :minRating) AND " +
            "(:maxRating IS NULL OR d.rating <= :maxRating)")
    List<Drinks> findByFilters(@Param("type") String type,
                               @Param("description") String description,
                               @Param("minPrice") BigDecimal minPrice,
                               @Param("maxPrice") BigDecimal maxPrice,
                               @Param("minRating") Double minRating,
                               @Param("maxRating") Double maxRating);


    boolean existsByName(String name);
    Optional<Drinks> findByName(String name);
    Optional<List<Drinks>> findByType(String type);
    Optional<List<Drinks>> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);
    Optional<List<Drinks>> findByRatingBetween(double ratingMin, double ratingMax);
    Optional<List<Drinks>> findByNameContaining(String name);
    Optional<List<Drinks>> findByDescriptionContaining(String description);

}
