package com.connectJPA.demo.service;


import com.connectJPA.demo.dto.request.DishUpdateRequest;
import com.connectJPA.demo.dto.request.DrinksCreationRequest;
import com.connectJPA.demo.dto.request.DrinksUpdateRequest;
import com.connectJPA.demo.dto.response.DishResponse;
import com.connectJPA.demo.dto.response.DrinksResponse;
import com.connectJPA.demo.dto.response.ProductResponse;
import com.connectJPA.demo.entity.Drinks;
import com.connectJPA.demo.entity.Product;
import com.connectJPA.demo.exception.AppException;
import com.connectJPA.demo.exception.ErrorCode;
import com.connectJPA.demo.mapper.DrinksMapper;
import com.connectJPA.demo.mapper.ProductMapper;
import com.connectJPA.demo.repository.DrinksRepository;
import com.connectJPA.demo.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DrinksService implements ProductService<DrinksCreationRequest, DrinksUpdateRequest, DrinksResponse> {
    DrinksRepository drinksRepository;
    DrinksMapper drinksMapper;
    ProductRepository productRepository;

    @Override
    public List<DrinksResponse> filterProducts(String type, String description, BigDecimal minPrice, BigDecimal maxPrice, Double minRating, Double maxRating) {
        log.info("Filtering drinks...");
        return drinksRepository.findByFilters(type, description, minPrice, maxPrice, minRating, maxRating)
                .stream()
                .map(drinksMapper::toDrinksResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DrinksResponse createProduct(DrinksCreationRequest request) {
        if (drinksRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.DRINKS_EXISTED);
        }
        Drinks drinks = drinksMapper.toDrinks(request);
        return drinksMapper.toDrinksResponse(drinksRepository.save(drinks));
    }

    public DrinksResponse getDrinksById(String id){
        return drinksMapper.toDrinksResponse(drinksRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED)));
    }

    @Override
    public List<DrinksResponse> getAllProducts() {
        return drinksRepository.findAll().stream()
                .map(drinksMapper::toDrinksResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DrinksResponse getProductByName(String name) {
        return drinksMapper.toDrinksResponse(drinksRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED)));
    }

    @Override
    public List<DrinksResponse> searchProductsByDescription(String keyword) {
        return drinksRepository.findByDescriptionContaining(keyword)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED))
                .stream()
                .map(drinksMapper::toDrinksResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DrinksResponse> searchProducts(String keyword) {
        return drinksRepository.findByNameContaining(keyword)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED))
                .stream()
                .map(drinksMapper::toDrinksResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DrinksResponse> getProductsByType(String type) {
        return drinksRepository.findByType(type)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED))
                .stream()
                .map(drinksMapper::toDrinksResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DrinksResponse> getProductsByPrice(BigDecimal priceMin, BigDecimal priceMax) {
        return drinksRepository.findByPriceBetween(priceMin, priceMax)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED))
                .stream()
                .map(drinksMapper::toDrinksResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DrinksResponse> getProductsByRating(double ratingMin, double ratingMax) {
        return drinksRepository.findByRatingBetween(ratingMin, ratingMax)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED))
                .stream()
                .map(drinksMapper::toDrinksResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public DrinksResponse updateProduct(String drinksId, DrinksUpdateRequest request) {
        Drinks drinks = drinksRepository.findById(drinksId)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED));
        drinksMapper.updateDrinks(drinks, request);
        return drinksMapper.toDrinksResponse(drinksRepository.save(drinks));
    }

    @Override
    public ProductResponse getProductById(String drinksId) {
        Drinks drinks = drinksRepository.findById(drinksId)
                .orElseThrow(() -> new AppException(ErrorCode.DRINKS_NOT_EXISTED));
        return drinksMapper.toDrinksResponse(drinks);
    }
}
