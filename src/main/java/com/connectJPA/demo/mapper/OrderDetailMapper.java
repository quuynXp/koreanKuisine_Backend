package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.OrderDetailRequest;
import com.connectJPA.demo.dto.response.OrderDetailResponse;
import com.connectJPA.demo.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(target = "totalPrice", expression = "java(orderDetailRequest.getUnitPrice().multiply(orderDetailRequest.getQuantity()))")
    OrderDetail toOrderDetail(OrderDetailRequest orderDetailRequest);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "productType", target = "productType")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "unitPrice", target = "unitPrice")
    @Mapping(source = "totalPrice", target = "totalPrice")
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

}




