package com.example.baekshop.Domain.Order.Entity;

import com.example.baekshop.Domain.OrderItem.Entity.OrderItem;
import com.example.baekshop.Domain.User.Entity.User;
import com.example.baekshop.Template.Entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User user;

    private LocalDateTime orderDate; //주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;  // 주문 상태

    // cascade:  부모가 바뀌면 자식도 바뀌고! 부모랑 자식이랑 연동됨.
    // orphanRemoval 부모와 연결이 떨어진 객체를 지울 수 있다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();


    // 생성한 주문 상품 객체를 이용해서 주문 객체를 만듬.
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // 주문 유저 설정
    public void setUser(User user){
        this.user = user;
    }

    // 주문한 유저와, 상태, 날짜 및 주문상품을 추가하여 order를 생성하는 메서드
    public static Order createOrder(User user, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setUser(user);
        for(OrderItem orderItem : orderItemList){
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }


    // 주문 취소
    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCEL;

        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }
}

