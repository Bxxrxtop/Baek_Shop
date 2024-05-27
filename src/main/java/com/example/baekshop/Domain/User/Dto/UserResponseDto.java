package com.example.baekshop.Domain.User.Dto;

import com.example.baekshop.Domain.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    public Long id;

    public String name;

    public String address;

    public static UserResponseDto from(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .build();
    }
}
