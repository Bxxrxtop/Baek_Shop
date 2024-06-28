package com.example.baekshop.Domain.Order.Service;

import com.example.baekshop.Domain.Item.Entity.Item;
import com.example.baekshop.Domain.Item.Repository.ItemRepository;
import com.example.baekshop.Domain.Order.Dto.OrderRequestDto;
import com.example.baekshop.Domain.Order.Dto.OrderResponseDto;
import com.example.baekshop.Domain.OrderItem.Dto.OrderItemDto;
import com.example.baekshop.Domain.OrderItem.Entity.OrderItem;
import com.example.baekshop.Domain.User.Entity.User;
import com.example.baekshop.Domain.Order.Entity.Order;
import com.example.baekshop.Domain.Order.Repository.OrderRepository;
import com.example.baekshop.Domain.User.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final ItemRepository itemRepository;

    public void createOrderItem(String email, List<OrderRequestDto> orderDtoList) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User findUser = user.get();   // 로그인한 유저 담기
            List<OrderItem> orderItemList = new ArrayList<>(); // 주문상품 리스트 생성.

            // 주문할 상품 리스트
            for (OrderRequestDto orderDto : orderDtoList) {
                Item item = itemRepository.findById(orderDto.getItemId())
                        .orElseThrow(EntityNotFoundException::new);

                OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
                orderItemList.add(orderItem); // 밑에 담아줌
            }

            Order order = Order.createOrder(findUser, orderItemList); // 회원이랑 주문할 상품 리스트들을 주문에 담음
            orderRepository.save(order);
        }
    }


    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrder(String email){
        List<Order> orders = orderRepository.findOrders(email); // 주문 목록
        Long totlaCount = orderRepository.countOrder(email); // 주문의 총 개수

        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();   // response할 주문 리스트

        for(Order order : orders){
            OrderResponseDto orderResponseDto = new OrderResponseDto(order); // entity -> dto
            List<OrderItem> orderItems = order.getOrderItems();              // order entity에 있는 주문상품 리스트

            for(OrderItem orderItem : orderItems){
                OrderItemDto orderItemDto = new OrderItemDto(orderItem);  // entity -> dto
                orderResponseDto.addOrderItemDto(orderItemDto);          // response에 추가
            }
            orderResponseDtos.add(orderResponseDto);      // 주문 response 리스트에 추가
        }
        return orderResponseDtos;
    }


    public void deleteOrder(String email, Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        if(order.getUser().getEmail().equals(email)){       // 로그인 유저와 주문한 유저가 같을시 삭제
            order.cancelOrder();
        }

    }

}
