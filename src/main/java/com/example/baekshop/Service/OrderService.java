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
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public void createOrder(String email, List<CreateOrderRequestDto> createOrderReqDtos){
       createOrderReqDtos.forEach(createOrderRequestDto -> {
           Order order = createOrderRequestDto.toEntity();  // dto -> entity로 변환
           log.info("[Order Service] 주문 생성 이름 ---> {}", createOrderRequestDto.getName());
           // 요청으로부터 userId 가져오기
           Optional<User> user = userRepository.findByEmail(email);
           if(user.isPresent()){
               User findUser = user.get();
               // login한 User의 Email과 요청한 사람의 Email이 같을 시
               order.setUser(findUser);
           }
           // 주문 생성 및 사용자와 연결
           orderRepository.save(order);
       });
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(String email,Long orderId) {
        try {
            // db에서 orderId에 맞는 order 가져오기
            Optional<Order> order = orderRepository.findById(orderId);
            Optional<User> user = userRepository.findByEmail(email);
            if (order.isPresent() && user.isPresent()) {
                Order findOrder = order.get();
                User loginUser = user.get();
                // entity -> dto
                if (findOrder.getUser().equals(loginUser)) {   // 로그인한 User와 Order의 User 비교
                    return OrderResponseDto.from(findOrder);
                }
                else{
                    throw new BadRequestException("로그인한 유저와 주문 유저가 다릅니다. " + orderId);
                }
            } else {
                throw new NoSuchElementException("주문을 찾을 수 없습니다: " + orderId);
            }
        } catch (Exception e) {
            log.error("주문 가져오는 중 오류 발생 ---> {}", e.getMessage());
            throw new RuntimeException("주문을 가져오는 중 오류 발생", e);
        }
    }


    public void updateOrder(String email ,Long orderId, UpdateOrderRequestDto reqdto){
        // repository findById 같은 메소드로 db에서 가져오기
        try{
            Optional<Order> order = orderRepository.findById(orderId);
            Optional<User> user = userRepository.findByEmail(email);
            if(user.isPresent() && order.isPresent()){
                User findUser = user.get();              // login한 User
                Order findOrder = order.get();
                if(findOrder.getUser().equals(findUser)){       // order의 User와 login한 User 같은지 비교.
                    findOrder.update(reqdto);                   // 같다면 update 후 저장.
                    orderRepository.save(findOrder);
                }
            }

        } catch (Exception e){
            log.error("[Order Service] 해당 Id 주문 가져오기 실패 ---> {}", orderId);
        }
    }

    public void deleteOrder(String email ,Long orderId){
        // repository에서 delete 메소드
        try{
            Optional<Order> order = orderRepository.findById(orderId);
            Optional<User> user = userRepository.findByEmail(email);
            if(user.isPresent() && order.isPresent()){
                User findUser = user.get();              // login한 User
                Order findOrder = order.get();
                if(findOrder.getUser().equals(findUser)){       // order의 User와 login한 User 같은지 비교.
                    orderRepository.deleteById(orderId);
                }
            }
        } catch (Exception e){
            log.error("[Order Service] 해당 Id 주문 삭제 실패 ---> {}", orderId);
        }
    }

}
