package com.example.baekshop.Domain.Order.Controller;

import com.example.baekshop.Domain.Order.Dto.OrderRequestDto;
import com.example.baekshop.Domain.Order.Service.OrderService;
import com.example.baekshop.Domain.Order.Dto.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order") // uri가 ~/user로 시작하는 요청을 받습니다.
@Tag(name = "주문 API", description = "주문 관련 API")
public class OrderController {

    private final OrderService orderService;
    // 해당 id의 주문을
    @Operation(method = "POST", summary = "주문 생성", description = "주문을 생성합니다. 생성할 주문 List를 Body에 담아 전송합니다.")
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal UserDetails userDetails,
                                         @RequestBody List<OrderRequestDto> orderRequestDtos) {
        try{
            orderService.createOrderItem(userDetails.getUsername(),orderRequestDtos);
            return ResponseEntity.status(HttpStatus.CREATED).body("주문 생성 저장 완료");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 저장 실패" + e.getMessage());
        }
    }


    @Operation(method = "GET", summary = "주문 조회", description = "로그인한 유저의 전체 주문을 조회합니다.")
    @GetMapping("")
    public ResponseEntity<?> getOrder(@AuthenticationPrincipal UserDetails userDetails) {
        List<OrderResponseDto> orderResponseDto = orderService.getOrder(userDetails.getUsername());
        return ResponseEntity.ok(orderResponseDto);
    }

// 주문 수정은 이미 주문이 들어간 상태인데 개별 주문상품들을 수정하는 것은 바람직하지 않다고 생각해서 뺏습니다.


    @Operation(method = "DELETE", summary = "주문 삭제", description = "단일 주문을 삭제합니다. 파라미터로 주문id를 받아서 요청합니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrder(@AuthenticationPrincipal UserDetails userDetails
                                            ,@RequestParam("id") Long id) {
        try{
            orderService.deleteOrder(userDetails.getUsername(), id);
            return ResponseEntity.status(HttpStatus.OK).body("주문 삭제 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 삭제 실패");
        }
    }


}