package com.example.baekshop.Service;

import com.example.baekshop.Dto.CreateOrderRequestDto;
import com.example.baekshop.Dto.OrderResponseDto;
import com.example.baekshop.Dto.UpdateOrderRequestDto;
import com.example.baekshop.Entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void createOrder(CreateOrderRequestDto reqdto){
        Order order = reqdto.toEntity();    // dto -> entity로 변환
        // repository 저장 메소드
    }

    public OrderResponseDto getOrder(Long orderId){
        // repository findById 같은 메소드로 db에서 가져오기

        Order order = new Order();   // 위 메소드를 통해 가져온 order

        return OrderResponseDto.from(order);
    }


    public void updateOrder(Long orderId, UpdateOrderRequestDto reqdto){
        // repository findById 같은 메소드로 db에서 가져오기

        Order order = new Order();   // 위 메소드를 통해 가져온 order

        order.update(reqdto);
        // 저장부분
    }

    public void deleteOrder(Long orderId){
        // repository에서 delete 메소드
    }

}
