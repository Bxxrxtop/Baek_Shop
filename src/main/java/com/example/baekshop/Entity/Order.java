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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long id;
    //상품 이름
    @Column(name = "name", nullable = false)
    public String name;

    //수량
    @Column(name = "quantity")
    public int quantity;

    //가격
    @Column(name = "price")
    public int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User user;


    // Setter 대신에 update 사용 -> 메소드를 통해서 접근! -> 유지보수 용이
    public void update(UpdateOrderRequestDto updateOrderReqDto){
        this.name = updateOrderReqDto.getName();
        this.quantity = updateOrderReqDto.getQuantity();
        this.price = updateOrderReqDto.getPrice();
    }

    public void setUser(User user){
        this.user = user;
    }
}
