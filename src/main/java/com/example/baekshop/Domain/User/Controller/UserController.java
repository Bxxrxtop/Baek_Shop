package com.example.baekshop.Domain.User.Controller;

import com.example.baekshop.Domain.User.Dto.CreateUserRequestDto;
import com.example.baekshop.Domain.User.Dto.UpdateUserRequestDto;
import com.example.baekshop.Domain.User.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j //로그 출력을 도와주는 어노테이션
@RestController
@RequiredArgsConstructor
@RequestMapping("/user") // uri가 ~/user로 시작하는 요청을 받습니다.
@Tag(name = "사용자 API", description = "사용자 관련 API입니다.")
public class UserController {

    private final UserService userService;

    @Operation(method = "POST", summary = "사용자 생성", description = "사용자를 생성합니다. dto를 담아 Body에 저장합니다.")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto dto) {
        try{
            userService.createUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("사용자 생성 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사용자 생성 실패");
        }
    }

    // 2. 사용자를 조회하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용하여 사용자의 ID를 출력해줍니다. return 값은 "사용자 조회"입니다.

    @Operation(method = "GET", summary = "사용자 조회", description = "로그인한 사용자의 정보를 담아 전송합니다.")
    @GetMapping("/")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("User Email ---> {}", userDetails.getUsername());

        return ResponseEntity.ok(userService.getUser(userDetails.getUsername()));

    }

    // 3. 사용자를 수정하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용를여 사용자의 이름, 주소를 출력해줍니다. return 값은 "사용자 수정"입니다.
    @Operation(method = "PUT", summary = "사용자 수정", description = "로그인한 사용자의 정보를 수정합니다.")
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestBody UpdateUserRequestDto dto) {
        try{
           userService.updateUser(userDetails.getUsername(), dto);
           return ResponseEntity.status(HttpStatus.OK).body("사용자 수정 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사용자 수정 실패");
        }
    }

    // 4. 사용자를 삭제하는 컨트롤러를 만듭니다.
    // 이때 log.info 이용하여 사용자의 ID를 출력해줍니다. return 값은 "사용자 삭제"입니다.
    @Operation(method = "DELETE", summary = "사용자 삭제", description = "로그인한 사용자를 삭제합니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        try{
            userService.deleteUser(userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body("사용자 삭제 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사용자 삭제 실패");
        }
    }
}