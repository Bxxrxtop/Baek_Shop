package com.example.baekshop.Service;

import com.example.baekshop.Dto.*;
import com.example.baekshop.Entity.Order;
import com.example.baekshop.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void createUser(CreateUserRequestDto reqdto){
        User user = reqdto.toEntity();    // dto -> entity로 변환
        // repository 저장 메소드
    }

    public UserResponseDto getUser(Long userId){
        // repository findById 같은 메소드로 db에서 가져오기

        User user = new User();   // 위 메소드를 통해 가져온 user

        return UserResponseDto.from(user);
    }


    public void updateUser(Long userId, UpdateUserRequestDto reqdto){
        // repository findById 같은 메소드로 db에서 가져오기

        User user = new User();   // 위 메소드를 통해 가져온 user

        user.update(reqdto);
        // 저장부분
    }

    public void deleteUser(Long userId){
        // repository에서 delete 메소드
    }
}
