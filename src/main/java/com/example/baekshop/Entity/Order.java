package com.example.baekshop.Entity;

import com.example.baekshop.Dto.UpdateOrderRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //상품 이름
    public String name;

    //수량
    public int quantity;

    //가격
    public int price;


    // Setter 대신에 update 사용 -> 메소드를 통해서 접근! -> 유지보수 용이
    public void update(UpdateOrderRequestDto reqdto){
        this.name = reqdto.getName();
        this.quantity = reqdto.getQuantity();
        this.price = reqdto.getPrice();
    }

}
