package com.example.baekshop.Domain.OrderItem.Dto;


import com.example.baekshop.Domain.OrderItem.Entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private String name; //상품명
    private int count; //주문수량
    private int price; //주문금액

    public OrderItemDto(OrderItem orderItem) {
        this.name = orderItem.getItem().getName();
        this.count = orderItem.getCount();
        this.price = orderItem.getPrice();}
}
