package com.example.baekshop.Entity;


import com.example.baekshop.Dto.UpdateUserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;

    public String id;

    public String name;

    public String password;

    public String address;


    // Setter 대신 update 사용 -> 메소드를 통해서 접근! -> 유지보수 용이
    public void update(UpdateUserRequestDto updateUserReqDto){
        this.name = updateUserReqDto.getName();
        this.address = updateUserReqDto.getAddress();
    }
}
