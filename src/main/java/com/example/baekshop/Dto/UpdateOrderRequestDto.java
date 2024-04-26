package com.example.baekshop.Dto;

import com.example.baekshop.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.
@Getter
public class UpdateOrderRequestDto {

    //상품 이름
    public String name;

    //수량
    public int quantity;

    //가격
    public int price;

    public Order toEntity(){
        return Order.builder()
                .name(name)
                .quantity(quantity)
                .price(price)
                .build();
    }

}