package com.connectJPA.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Builder.Default
    BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;

    @Column(name = "orders_date")
    LocalDateTime ordersDate;

    // Add an OrderDetail to the order
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);   // Thêm OrderDetail vào danh sách
        orderDetail.setOrders(this);     // Thiết lập mối quan hệ ngược với Orders
        totalAmount = totalAmount.add(orderDetail.getTotalPrice()); // Cập nhật tổng số tiền
    }

    // Remove an OrderDetail from the order
    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetails.remove(orderDetail);
        orderDetail.setOrders(null);
        totalAmount = totalAmount.subtract(orderDetail.getTotalPrice());
    }
}
