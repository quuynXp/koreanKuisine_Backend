package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.OrderRequest;
import com.connectJPA.demo.dto.response.*;
import com.connectJPA.demo.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = OrderDetailMapper.class)
public interface OrderMapper {
    Orders toOrder(OrderRequest orderRequest);

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "order.orderDetails", target = "orderDetailResponses")
    OrderResponse toOrderResponse(Orders order);
}







