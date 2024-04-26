package com.example.baekshop.Dto;

import com.example.baekshop.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.
@Getter
public class UpdateUserRequestDto {

    public String name;

    public String address;

    public User toEntity(){
        return User.builder()
                .name(name)
                .address(address)
                .build();
    }
}