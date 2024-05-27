package com.example.baekshop.Domain.Item.Controller;

import com.example.baekshop.Domain.Item.Dto.CreateItemRequestDto;
import com.example.baekshop.Domain.Item.Dto.ItemResponseDto;
import com.example.baekshop.Domain.Item.Dto.UpdateItemRequestDto;
import com.example.baekshop.Domain.Item.Service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
@Tag(name = "상품 API", description = "상품 관련 API")
public class ItemController {
    private final ItemService itemService;

    @Operation(method = "POST", summary = "상품 등록", description = "단일 상품을 등록합니다. 등록할 상품 정보를 body에 담아 보냅니다.")
    @PostMapping("/create")
    public ResponseEntity<?> itemSave(@AuthenticationPrincipal UserDetails userDetails,
                                      @RequestBody CreateItemRequestDto createProductReqDto){
        try{
            itemService.saveItem(createProductReqDto, userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body("상품 저장 완료");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 저장 실패" + e.getMessage());
        }
    }

    @Operation(method = "GET", summary = "상품 조회", description = "해당 id의 상품을 조회합니다.")
    @GetMapping("/detail")
    public ResponseEntity<?> itemDetail(@RequestParam("id") Long id){
        ItemResponseDto itemResponseDto = itemService.getItem(id);
        return ResponseEntity.ok(itemResponseDto);
    }

    @Operation(method = "PUT", summary = "상픔 수정", description = "해당 id의 상품을 수정합니다.")
    @PutMapping("/update")
    public ResponseEntity<?> itemUpdate(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestBody UpdateItemRequestDto updateProductReqDto,
                                           @RequestParam("id") Long id) {
        try {
            itemService.updateOrder(id, updateProductReqDto);
            return ResponseEntity.status(HttpStatus.OK).body("상품 수정 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 수정 실패");
        }
    }

    @Operation(method = "DELETE", summary = "상품 삭제", description = "해당 id의 상품을 삭제합니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<?> itemDelete(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestParam("id") Long id) {
        try{
            itemService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.OK).body("상품 삭제 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 삭제 실패");
        }
    }
}

