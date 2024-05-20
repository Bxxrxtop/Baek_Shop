package com.example.baekshop.Controller;

import com.example.baekshop.Dto.CreateUserRequestDto;
import com.example.baekshop.Dto.UpdateUserRequestDto;
import com.example.baekshop.Repository.UserRepository;
import com.example.baekshop.Service.UserService;
import com.example.baekshop.UserDetails.CustomUserDetails;
import com.example.baekshop.UserDetails.CustomUserDetailsService;
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
public class UserController {

    private final UserService userService;

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

    @GetMapping("/")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("User Email ---> {}", userDetails.getUsername());

        return ResponseEntity.ok(userService.getUser(userDetails.getUsername()));

    }

    // 3. 사용자를 수정하는 컨트롤러를 만듭니다.
    // 이때 log.info를 이용를여 사용자의 이름, 주소를 출력해줍니다. return 값은 "사용자 수정"입니다.
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