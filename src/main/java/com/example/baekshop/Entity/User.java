package com.example.baekshop.Entity;


import com.example.baekshop.Dto.UpdateUserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public Long userId;

    @Column(name = "id", nullable = false)
    public String id;

    @Column(name = "name")
    public String name;

    @Column(name = "password")
    public String password;

    @Column(name = "address")
    public String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Order> orders;

    // Setter 대신 update 사용 -> 메소드를 통해서 접근! -> 유지보수 용이
    public void update(UpdateUserRequestDto updateUserReqDto){
        this.name = updateUserReqDto.getName();
        this.address = updateUserReqDto.getAddress();
    }
}
