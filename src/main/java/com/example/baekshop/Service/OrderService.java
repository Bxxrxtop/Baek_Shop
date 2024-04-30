package com.example.baekshop.Service;

import com.example.baekshop.Dto.CreateOrderRequestDto;
import com.example.baekshop.Dto.OrderResponseDto;
import com.example.baekshop.Dto.UpdateOrderRequestDto;
import com.example.baekshop.Entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    public void createOrder(List<CreateOrderRequestDto> createOrderReqDtos){
       createOrderReqDtos.forEach(createOrderRequestDto -> {
           log.info("[Order Service] 주문 생성 이름 ---> {}", createOrderRequestDto.getName());

           Order order = createOrderRequestDto.toEntity();  // dto -> entity로 변환
           // repository 저장 메소드
       });
    }

    public OrderResponseDto getOrder(Long orderId){
        // repository findById 같은 메소드로 db에서 가져오기
        log.info("[Order Service] 주문 가져오기 ID ---> {}", orderId);

        Order order = new Order();   // 위 메소드를 통해 가져온 order

        return OrderResponseDto.from(order);
    }


    public void updateOrder(Long orderId, UpdateOrderRequestDto reqdto){
        // repository findById 같은 메소드로 db에서 가져오기
        log.info("[Order Service] 주문 수정하기 ID ---> {}", orderId);

        Order order = new Order();   // 위 메소드를 통해 가져온 order

        order.update(reqdto);
        // 저장부분
    }

    public void deleteOrder(Long orderId){
        log.info("[Order Service] 주문 삭제하기 ID ---> {}", orderId);
        // repository에서 delete 메소드
    }

}
