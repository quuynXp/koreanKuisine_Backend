package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.OrderRequest;
import com.connectJPA.demo.dto.response.*;
import com.connectJPA.demo.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = OrderDetailMapper.class)
public interface OrderMapper {
    Orders toOrder(OrderRequest orderRequest);

    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "promoCode", target = "promoCode")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "ordersDate", target = "ordersDate")
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "orderDetails", target = "orderDetailResponses")
    OrderResponse toOrderResponse(Orders order);
}







