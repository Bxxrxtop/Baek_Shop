package com.example.baekshop.Domain.User.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {     // 두 토큰 전달 dto

    public String accessToken;
    public String refreshToken;
}
