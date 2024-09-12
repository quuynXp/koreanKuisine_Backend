package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.OrderDetailRequest;
import com.connectJPA.demo.dto.request.OrderRequest;
import com.connectJPA.demo.dto.response.OrderDetailResponse;
import com.connectJPA.demo.dto.response.OrderResponse;
import com.connectJPA.demo.entity.Order;
import com.connectJPA.demo.entity.OrderDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T15:24:50+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(OrderRequest orderRequest) {
        if ( orderRequest == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.orderDetails( orderDetailRequestListToOrderDetailList( orderRequest.getOrderDetails() ) );

        return order.build();
    }

    @Override
    public OrderResponse toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.orderId( order.getId() );
        orderResponse.orderDate( order.getOrderDate() );
        orderResponse.totalAmount( order.getTotalAmount() );
        orderResponse.orderDetails( orderDetailListToOrderDetailResponseList( order.getOrderDetails() ) );

        return orderResponse.build();
    }

    protected OrderDetail orderDetailRequestToOrderDetail(OrderDetailRequest orderDetailRequest) {
        if ( orderDetailRequest == null ) {
            return null;
        }

        OrderDetail.OrderDetailBuilder orderDetail = OrderDetail.builder();

        orderDetail.productId( orderDetailRequest.getProductId() );
        orderDetail.productName( orderDetailRequest.getProductName() );
        orderDetail.quantity( orderDetailRequest.getQuantity() );
        orderDetail.unitPrice( orderDetailRequest.getUnitPrice() );
        orderDetail.productType( orderDetailRequest.getProductType() );

        return orderDetail.build();
    }

    protected List<OrderDetail> orderDetailRequestListToOrderDetailList(List<OrderDetailRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetail> list1 = new ArrayList<OrderDetail>( list.size() );
        for ( OrderDetailRequest orderDetailRequest : list ) {
            list1.add( orderDetailRequestToOrderDetail( orderDetailRequest ) );
        }

        return list1;
    }

    protected OrderDetailResponse orderDetailToOrderDetailResponse(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailResponse.OrderDetailResponseBuilder orderDetailResponse = OrderDetailResponse.builder();

        orderDetailResponse.productId( orderDetail.getProductId() );
        orderDetailResponse.productName( orderDetail.getProductName() );
        orderDetailResponse.productType( orderDetail.getProductType() );
        orderDetailResponse.quantity( orderDetail.getQuantity() );
        orderDetailResponse.unitPrice( orderDetail.getUnitPrice() );

        return orderDetailResponse.build();
    }

    protected List<OrderDetailResponse> orderDetailListToOrderDetailResponseList(List<OrderDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetailResponse> list1 = new ArrayList<OrderDetailResponse>( list.size() );
        for ( OrderDetail orderDetail : list ) {
            list1.add( orderDetailToOrderDetailResponse( orderDetail ) );
        }

        return list1;
    }
}
