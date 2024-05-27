package com.example.baekshop.Domain.Item.Dto;

import com.example.baekshop.Domain.Item.Entity.Item;
import com.example.baekshop.Domain.Item.Entity.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
    private String name;
    private String info;
    private int price;
    private int stock;
    private ItemStatus itemStatus;
    private String userName;

    public static ItemResponseDto from(Item item){
        return ItemResponseDto.builder()
                .name(item.getName())
                .info(item.getInfo())
                .price(item.getPrice())
                .stock(item.getStock())
                .itemStatus(item.getItemStatus())
                .userName(item.getUserName())
                .build();
    }
}
