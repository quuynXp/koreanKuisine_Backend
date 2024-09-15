package com.connectJPA.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderDetail> orderDetails = new ArrayList<>();

    BigDecimal totalAmount = BigDecimal.ZERO;

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        totalAmount = totalAmount.add(orderDetail.getTotalPrice());
        orderDetail.setCart(this);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetails.remove(orderDetail);
        totalAmount = totalAmount.subtract(orderDetail.getTotalPrice());
        orderDetail.setCart(null);
    }

    public void updateTotalAmount(BigDecimal amount) {
        this.totalAmount = amount;
    }
}
