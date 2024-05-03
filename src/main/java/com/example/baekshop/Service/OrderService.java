package com.example.baekshop.Service;

import com.example.baekshop.Dto.CreateOrderRequestDto;
import com.example.baekshop.Dto.OrderResponseDto;
import com.example.baekshop.Dto.UpdateOrderRequestDto;
import com.example.baekshop.Entity.Order;
import com.example.baekshop.Entity.User;
import com.example.baekshop.Repository.OrderRepository;
import com.example.baekshop.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public void createOrder(List<CreateOrderRequestDto> createOrderReqDtos){
       createOrderReqDtos.forEach(createOrderRequestDto -> {
           Order order = createOrderRequestDto.toEntity();  // dto -> entity로 변환
           log.info("[Order Service] 주문 생성 이름 ---> {}", createOrderRequestDto.getName());
           // 요청으로부터 userId 가져오기
           Optional<User> userId = userRepository.findById(createOrderRequestDto.getUserId());
           if(userId.isPresent()){
               User findUser = userId.get();
               order.setUser(findUser);
           }
           // 주문 생성 및 사용자와 연결

           orderRepository.save(order);
       });
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(Long orderId){
        try{
            // db에서 orderId에 맞는 order 가져오기
            Optional<Order> order = orderRepository.findById(orderId);
            if(order.isPresent()){
                Order findOrder = order.get();
                // entity -> dto
                return OrderResponseDto.from(findOrder);
            }

        }catch (Exception e){
            log.error("주문 가져오는 중 오류 발생 ---> {}", e.getMessage());
        }
        return null;
    }


    public void updateOrder(Long orderId, UpdateOrderRequestDto reqdto){
        // repository findById 같은 메소드로 db에서 가져오기
        try{
            Optional<Order> order = orderRepository.findById(orderId);
            if(order.isPresent()){
                Order findOrder = order.get();
                findOrder.update(reqdto);
                orderRepository.save(findOrder);
            }
        } catch (Exception e){
            log.error("[Order Service] 해당 Id 주문 가져오기 실패 ---> {}", orderId);
        }
    }

    public void deleteOrder(Long orderId){
        log.info("[Order Service] 주문 삭제하기 ID ---> {}", orderId);
        // repository에서 delete 메소드
        try{
            orderRepository.deleteById(orderId);
        } catch (Exception e){
            log.error("[Order Service] 해당 Id 주문 삭제 실패 ---> {}", orderId);
        }
    }

}
