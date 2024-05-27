package com.example.baekshop.Domain.Item.Dto;


import com.example.baekshop.Domain.Item.Entity.Item;
import com.example.baekshop.Domain.Item.Entity.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateItemRequestDto {
    private String name;
    private String info;
    private int price;
    private int stock;
    private ItemStatus itemStatus;
    private String userName;

    public Item toEntity(){
        return Item.builder()
                .name(name)
                .info(info)
                .price(price)
                .stock(stock)
                .itemStatus(itemStatus)
                .userName(userName)
                .build();
    }
}
