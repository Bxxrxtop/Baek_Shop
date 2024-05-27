package com.example.baekshop.Domain.Order.Dto;

import com.example.baekshop.Domain.Order.Entity.Order;
import com.example.baekshop.Domain.Order.Entity.OrderStatus;
import com.example.baekshop.Domain.OrderItem.Dto.OrderItemDto;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long orderId;
    private String orderDate;
    private OrderStatus orderStatus; //주문 상태

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }

    public OrderResponseDto(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

}

