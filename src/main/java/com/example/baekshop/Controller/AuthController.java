package com.example.baekshop.Controller;

import com.example.baekshop.Dto.JwtDto;
import com.example.baekshop.Service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    //토큰 재발급 API
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody JwtDto jwtDto) {

        log.info("[ Auth Controller ] 토큰을 재발급합니다. ");

        return ResponseEntity.ok(authService.reissueToken(jwtDto));
    }
}
