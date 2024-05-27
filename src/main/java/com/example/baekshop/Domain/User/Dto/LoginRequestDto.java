package com.example.baekshop.Domain.User.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "[ERROR] 이메일은 입력은 필수입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}", message = "[ERROR] 이메일 형식에 맞지 않습니다.")
    public String email;


    @NotBlank(message = "[ERROR] 이메일은 입력은 필수입니다.")
    @Size(min = 8, message = "[ERROR] 비밀번호는 최소 8자리 이상이어야 합니다." )
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,64}&", message = "[ERROR] 비밀번호는 8~64자이며 특수문자 한 개를 포함해야 합니다")
    public String password;
}
