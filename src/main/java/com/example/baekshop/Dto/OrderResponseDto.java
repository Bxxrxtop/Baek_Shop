package com.example.baekshop.Dto;

import com.example.baekshop.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private String name;
    public int quantity;
    public int price;

    public static OrderResponseDto from(Order order){
        return OrderResponseDto.builder()
                .name(order.getName())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();
    }
}
