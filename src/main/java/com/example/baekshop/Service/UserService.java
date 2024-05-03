package com.example.baekshop.Service;

import com.example.baekshop.Dto.*;
import com.example.baekshop.Entity.Order;
import com.example.baekshop.Entity.User;
import com.example.baekshop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void createUser(CreateUserRequestDto createUserReqDto){
        User user = createUserReqDto.toEntity();    // dto -> entity로 변환
        // repository 저장 메소드
        try{
            userRepository.save(user);
        } catch (Exception e){
            log.error("[ User Service ] 사용자 저장 실패 ID ---> {}", user.getUserId());
        }
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long userId){
        // repository findById 같은 메소드로 db에서 가져오기
        try{
            Optional<User> user = userRepository.findById(userId);
            if(user.isPresent()){
                User findUser = user.get();
                return UserResponseDto.from(findUser);
            }
        }catch (Exception e){
            log.error("[ User Service ] 사용자 가져오기 실패 ID ---> {}", userId);
        }
        return null;
    }


    public void updateUser(Long userId, UpdateUserRequestDto updateUserReqDto){
        try{
            Optional<User> user = userRepository.findById(userId);
            if(user.isPresent()){
                User findUser = user.get();
                findUser.update(updateUserReqDto);
                userRepository.save(findUser);
            }
        }catch (Exception e){
            log.error("[ User Service ] 사용자 수정 실패 ID ---> {}", userId);
        }
    }

    public void deleteUser(Long userId){
        log.info("[ User Service ] 사용자 삭제하기 ID ---> {}", userId);
        try{
            userRepository.deleteById(userId);
        }catch (Exception e){
            log.error("[ User Service ] 사용자 삭제 실패 ID ---> {}", userId);
        }
    }
}
