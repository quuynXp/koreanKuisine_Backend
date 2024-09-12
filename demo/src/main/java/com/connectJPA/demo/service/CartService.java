package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.*;
import com.connectJPA.demo.exception.AppException;
import com.connectJPA.demo.exception.ErrorCode;
import com.connectJPA.demo.repository.CartRepository;
import com.connectJPA.demo.repository.DishRepository;
import com.connectJPA.demo.repository.DrinksRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepository cartRepository;
    DishRepository dishRepository;
    DrinksRepository drinksRepository;

    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotalAmount(BigDecimal.ZERO);
        return cartRepository.save(cart);
    }

    public void addProductToCart(Cart cart, String productId, BigDecimal quantity) {
        Optional<Dish> dishOpt = dishRepository.findById(productId);
        Optional<Drinks> drinksOpt = drinksRepository.findById(productId);

        if (dishOpt.isPresent()) {
            addDishToCart(cart, dishOpt.get(), quantity);
        } else if (drinksOpt.isPresent()) {
            addDrinksToCart(cart, drinksOpt.get(), quantity);
        } else {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    private void addDishToCart(Cart cart, Dish dish, BigDecimal quantity) {
        System.out.println("Image URL: " + dish.getImageUrl());
        Optional<OrderDetail> existingDetailOpt = cart.getOrderDetails().stream()
                .filter(detail -> detail.getProductId().equals(dish.getId()))
                .findFirst();

        if (existingDetailOpt.isPresent()) {
            // Nếu sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng và giá tổng
            OrderDetail existingDetail = existingDetailOpt.get();
            System.out.println("OrderDetail imageUrl: " + existingDetail.getImageUrl());


            existingDetail.setQuantity(existingDetail.getQuantity().add(quantity));
            existingDetail.setTotalPrice(existingDetail.getUnitPrice().multiply(existingDetail.getQuantity()));
        } else {
            // Thêm sản phẩm mới vào giỏ hàng

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductName(dish.getName());
            orderDetail.setProductId(dish.getId());
            orderDetail.setQuantity(quantity);
            orderDetail.setImageUrl(dish.getImageUrl());
            orderDetail.setProductType("Dish");
            orderDetail.setUnitPrice(dish.getPrice());
            orderDetail.setTotalPrice(dish.getPrice().multiply(quantity));
            cart.addOrderDetail(orderDetail);
        }

        cart.setTotalAmount(calculateTotalAmount(cart));
        cartRepository.save(cart);
    }

    private void addDrinksToCart(Cart cart, Drinks drinks, BigDecimal quantity) {
        System.out.println("Image URL: " + drinks.getImageUrl());
        Optional<OrderDetail> existingDetailOpt = cart.getOrderDetails().stream()
                .filter(detail -> detail.getProductId().equals(drinks.getId()))
                .findFirst();

        if (existingDetailOpt.isPresent()) {
            // Nếu đồ uống đã tồn tại trong giỏ hàng, cập nhật số lượng và giá tổng
            OrderDetail existingDetail = existingDetailOpt.get();
            existingDetail.setQuantity(existingDetail.getQuantity().add(quantity));
            existingDetail.setTotalPrice(existingDetail.getUnitPrice().multiply(existingDetail.getQuantity()));
        } else {
            // Thêm đồ uống mới vào giỏ hàng
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductName(drinks.getName());
            orderDetail.setProductId(drinks.getId());
            orderDetail.setQuantity(quantity);
            orderDetail.setImageUrl(drinks.getImageUrl());
            orderDetail.setProductType("Drinks");
            orderDetail.setUnitPrice(drinks.getPrice());
            orderDetail.setTotalPrice(drinks.getPrice().multiply(quantity));
            cart.addOrderDetail(orderDetail);
        }

        cart.setTotalAmount(calculateTotalAmount(cart));
        cartRepository.save(cart);
    }

    public void removeProductFromCart(Cart cart, String productId) {
        boolean removed = cart.getOrderDetails().removeIf(detail -> detail.getProductId().equals(productId));
        if (removed) {
            cart.setTotalAmount(calculateTotalAmount(cart));
            cartRepository.save(cart);
        } else {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    public BigDecimal calculateTotalAmount(Cart cart) {
        return cart.getOrderDetails().stream()
                .map(OrderDetail::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Optional<Cart> findById(String cartId) {
        return cartRepository.findById(cartId);
    }

    public Optional<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }
}
