package com.example.baekshop.Domain.User.Service;

import com.example.baekshop.Domain.User.Dto.CreateUserRequestDto;
import com.example.baekshop.Domain.User.Entity.User;
import com.example.baekshop.Domain.User.Repository.UserRepository;
import com.example.baekshop.Domain.User.Dto.UpdateUserRequestDto;
import com.example.baekshop.Domain.User.Dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(CreateUserRequestDto createUserReqDto){
        User user = createUserReqDto.toEntity(passwordEncoder);    // dto -> entity로 변환
        // repository 저장 메소드
        try{
            userRepository.save(user);
        } catch (Exception e){
            log.error("[ User Service ] 사용자 저장 실패 ID ---> {}", user.getId());
        }
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(String email){
        // repository findById 같은 메소드로 db에서 가져오기
        try{
            Optional<User> user = userRepository.findByEmail(email);
            if(user.isPresent()){
                User findUser = user.get();
                return UserResponseDto.from(findUser);
            }
            else{
                throw new NoSuchElementException("User를 찾을 수 없습니다: " + email);
            }
        } catch (Exception e){
            log.error("[ User Service ] 사용자 가져오기 실패 Email ---> {}", email);
            throw new RuntimeException("User 가져오는 중 오류 발생", e);
        }
    }


    public void updateUser(String email, UpdateUserRequestDto updateUserReqDto){
        try{
            Optional<User> user = userRepository.findByEmail(email);
            if(user.isPresent()){
                User findUser = user.get();
                findUser.update(updateUserReqDto);
                userRepository.save(findUser);
            }
        }catch (Exception e){
            log.error("[ User Service ] 사용자 수정 실패 Email ---> {}", email);
        }
    }

    public void deleteUser(String email){
        log.info("[ User Service ] 사용자 삭제하기 Email ---> {}", email);
        try{
            Optional<User> user = userRepository.findByEmail(email);
            if(user.isPresent()) {
                User findUser = user.get();
                Long userId = findUser.getId();
                userRepository.deleteById(userId);
            }
        }catch (Exception e){
            log.error("[ User Service ] 사용자 삭제 실패 Email ---> {}", email);
        }
    }
}
