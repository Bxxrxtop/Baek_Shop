package com.example.baekshop.Service;

import com.example.baekshop.Dto.*;
import com.example.baekshop.Entity.Order;
import com.example.baekshop.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public void createUser(CreateUserRequestDto createUserReqDto){
        log.info("[Order Service] 사용자 생성 이름 ---> {}", createUserReqDto.getName());
        User user = createUserReqDto.toEntity();    // dto -> entity로 변환
        // repository 저장 메소드
    }

    public UserResponseDto getUser(Long userId){
        // repository findById 같은 메소드로 db에서 가져오기
        log.info("[ User Service ] 사용자 가져오기 ID ---> {}", userId);

        User user = new User();   // 위 메소드를 통해 가져온 user

        return UserResponseDto.from(user);
    }


    public void updateUser(Long userId, UpdateUserRequestDto updateUserReqDto){
        // repository findById 같은 메소드로 db에서 가져오기
        log.info("[ User Service ] 사용자 수정 ID ---> {}",userId);

        User user = new User();   // 위 메소드를 통해 가져온 user

        user.update(updateUserReqDto);
        // 저장부분
    }

    public void deleteUser(Long userId){
        log.info("[ User Service ] 사용자 삭제 ID ---> {}",userId);
        // repository에서 delete 메소드

    }
}
