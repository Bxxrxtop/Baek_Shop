package com.example.baekshop.Dto;

import com.example.baekshop.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    public String name;

    public String id;

    public String address;

    public static UserResponseDto from(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .id(user.getId())
                .address(user.getAddress())
                .build();
    }
}
