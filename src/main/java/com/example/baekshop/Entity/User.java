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
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    // roles 추가
    @Column(name = "roles")
    private String roles;



    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    // Setter 대신 update 사용 -> 메소드를 통해서 접근! -> 유지보수 용이
    public void update(UpdateUserRequestDto updateUserReqDto){
        this.name = updateUserReqDto.getName();
        this.address = updateUserReqDto.getAddress();
    }
}
