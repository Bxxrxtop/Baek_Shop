package com.example.baekshop.Domain.OrderItem.Entity;


import com.example.baekshop.Domain.Item.Entity.Item;
import com.example.baekshop.Domain.Order.Entity.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    private int price; // 주문가격

    private int count; // 수량

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setPrice(item.getPrice());

        item.removeStock(count);

        return orderItem;
    }

    public int getTotalPrice(){
        return price * count;
    }

    public void cancel(){
        this.getItem().addStock(count);   // 주문 취소시 해당 item 재고 다시 더해줌.
    }
}
