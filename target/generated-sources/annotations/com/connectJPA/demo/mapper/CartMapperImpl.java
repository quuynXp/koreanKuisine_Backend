package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.CartRequest;
import com.connectJPA.demo.dto.response.CartResponse;
import com.connectJPA.demo.dto.response.OrderDetailResponse;
import com.connectJPA.demo.entity.Cart;
import com.connectJPA.demo.entity.OrderDetail;
import com.connectJPA.demo.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T15:24:49+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class CartMapperImpl implements CartMapper {

    @Override
    public CartResponse toCartResponse(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse.CartResponseBuilder cartResponse = CartResponse.builder();

        cartResponse.userId( cartUserId( cart ) );
        cartResponse.id( cart.getId() );
        cartResponse.orderDetails( orderDetailListToOrderDetailResponseList( cart.getOrderDetails() ) );
        cartResponse.totalAmount( cart.getTotalAmount() );

        return cartResponse.build();
    }

    @Override
    public Cart toCart(CartRequest cartRequest) {
        if ( cartRequest == null ) {
            return null;
        }

        Cart.CartBuilder cart = Cart.builder();

        return cart.build();
    }

    private String cartUserId(Cart cart) {
        if ( cart == null ) {
            return null;
        }
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        String id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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
