package com.connectJPA.demo.controller;

import com.connectJPA.demo.dto.request.AddProductRequest;
import com.connectJPA.demo.dto.request.RemoveProductRequest;
import com.connectJPA.demo.dto.response.ProductResponse;
import com.connectJPA.demo.entity.Product;

import com.connectJPA.demo.dto.request.CartRequest;
import com.connectJPA.demo.dto.response.ApiResponse;
import com.connectJPA.demo.dto.response.CartResponse;
import com.connectJPA.demo.entity.Cart;
import com.connectJPA.demo.entity.User;
import com.connectJPA.demo.mapper.CartMapper;
import com.connectJPA.demo.repository.UserRepository;
import com.connectJPA.demo.service.CartService;
import com.connectJPA.demo.service.DishService;
import com.connectJPA.demo.service.DrinksService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/carts")
public class CartController {
    CartService cartService;
    UserRepository userRepository;
    DishService dishService;
    DrinksService drinksService;
    CartMapper cartMapper = CartMapper.INSTANCE;

    @GetMapping("/current")
    public ApiResponse<CartResponse> getCurrentCart(@RequestParam String userName) {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()) {
            return ApiResponse.<CartResponse>builder()
                    .result(null)
                    .message("Người dùng không tồn tại.")
                    .build();
        }

        Optional<Cart> cartOptional = cartService.findByUser(userOptional.get());
        if (cartOptional.isEmpty()) {
            return ApiResponse.<CartResponse>builder()
                    .result(null)
                    .message("Giỏ hàng không tồn tại.")
                    .build();
        }

        return ApiResponse.<CartResponse>builder()
                .result(cartMapper.toCartResponse(cartOptional.get()))
                .message("Giỏ hàng đã được tải thành công.")
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<CartResponse> createCart(@RequestBody CartRequest cartRequest) {
        Optional<User> userOptional = userRepository.findByUsername(cartRequest.getUserName());
        if (userOptional.isEmpty()) {
            return ApiResponse.<CartResponse>builder()
                    .result(null)
                    .message("Người dùng không tồn tại.")
                    .build();
        }
        Cart cart = cartService.createCart(userOptional.get());

        return ApiResponse.<CartResponse>builder()
                .result(cartMapper.toCartResponse(cart))
                .message("Giỏ hàng đã được tạo thành công.")
                .build();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartResponse>> addProductToCart(@RequestBody AddProductRequest request) {
        Optional<Cart> cartOptional = cartService.findById(request.getCartId());
        if (cartOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<CartResponse>builder()
                            .result(null)
                            .message("Giỏ hàng không tồn tại.")
                            .build());
        }

        // Load product
        String productId = request.getProductId();
        BigDecimal quantity = request.getQuantity();
        cartService.addProductToCart(cartOptional.get(), productId, quantity);

        return ResponseEntity.ok(
                ApiResponse.<CartResponse>builder()
                        .result(cartMapper.toCartResponse(cartOptional.get()))
                        .message("Sản phẩm đã được thêm vào giỏ hàng.")
                        .build()
        );
    }


    @PostMapping("/remove")
    public ApiResponse<CartResponse> removeProductFromCart(@RequestBody RemoveProductRequest request) {
        Optional<Cart> cartOptional = cartService.findById(request.getCartId());
        if (cartOptional.isEmpty()) {
            return ApiResponse.<CartResponse>builder()
                    .result(null)
                    .message("Giỏ hàng không tồn tại.")
                    .build();
        }

        Cart cart = cartOptional.get();
        cartService.removeProductFromCart(cart, request.getProductId());

        return ApiResponse.<CartResponse>builder()
                .result(cartMapper.toCartResponse(cart))
                .message("Sản phẩm đã được xóa khỏi giỏ hàng.")
                .build();
    }
}
