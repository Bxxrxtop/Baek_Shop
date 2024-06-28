package com.example.baekshop.Domain.Item.Entity;


import com.example.baekshop.Domain.Item.Dto.UpdateItemRequestDto;
import com.example.baekshop.Template.Entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "info", nullable = false)
    private String info;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    private String userName;

    public void setUserName(String name){
        this.userName = name;
    }

    public void update(UpdateItemRequestDto updateProductReqDto){
        this.name = updateProductReqDto.getName();
        this.info = updateProductReqDto.getInfo();
        this.price = updateProductReqDto.getPrice();
        this.stock = updateProductReqDto.getStock();
        this.itemStatus = updateProductReqDto.getItemStatus();
    }

    public void removeStock(int stock){
        int rest = this.stock - stock;
        if(rest<0){
            throw new NoSuchElementException();
        } else if (rest == 0) {
            this.itemStatus = ItemStatus.SOLD_OUT;    // 남은 재고가 0이면 SOLD_OUT
        }
        this.stock = rest;
    }

    public void addStock(int stock){
        this.stock += stock;
        if(this.stock > 0 && this.itemStatus != ItemStatus.SELL){   // 재고가 0이아니고 SELL 상태가 아닌경우
            this.itemStatus = ItemStatus.SELL;                      // SELL로 바꿔준다.
        }
    }

}
