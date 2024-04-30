package com.example.baekshop.Controller;

import com.example.baekshop.Dto.CreateOrderRequestDto;
import com.example.baekshop.Dto.OrderResponseDto;
import com.example.baekshop.Dto.UpdateOrderRequestDto;
import com.example.baekshop.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order") // uri가 ~/user로 시작하는 요청을 받습니다.
public class OrderController {

    private final OrderService orderService;

    // 1. 주문을 생성하는 컨트롤러를 만듭니다. 이때 return 값은 "주문 생성하기"입니다. -> 주문은 리스트 형태로 요청을 보내주세요!
    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody List<CreateOrderRequestDto> dtos) {
        try{
            orderService.createOrder(dtos);
            return ResponseEntity.status(HttpStatus.CREATED).body("주문 생성 저장 완료");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 저장 실패");
        }
    }

    // 2. 주문을 가져오는 컨트롤러를 만듭니다. 이때 return 값은 "주문 가져오기"입니다.
    @GetMapping("")
    public ResponseEntity<?> getOrder(@RequestParam("id") Long id) {
        OrderResponseDto orderResponseDto = orderService.getOrder(id);
        return ResponseEntity.ok(orderResponseDto);
    }

    // 3. 주문을 수정하는 컨트롤러를 만듭니다. 이때 return 값은 "주문 수정하기"입니다.
    @PutMapping("")
    public ResponseEntity<?> updateOrder(@RequestParam("id") Long id,
                                         @RequestBody UpdateOrderRequestDto dto) {
        try{
            orderService.updateOrder(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body("주문 수정 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 수정 실패");
        }
    }

    // 4. 주문을 삭제하는 컨트롤러를 만듭니다. 이때 return 값은 "주문 삭제하기"입니다.
    @DeleteMapping("")
    public ResponseEntity<?> deleteOrder(@RequestParam("id") Long id) {
        try{
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("주문 삭제 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 삭제 실패");
        }
    }


}