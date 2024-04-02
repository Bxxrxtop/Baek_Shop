package com.example.baekshop.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.
@Getter //Getter 메서드를 자동으로 생성해줍니다.
public class CreateUserRequestDto {

    public String name;

    public String id;

    public String password;

    public String address;

}