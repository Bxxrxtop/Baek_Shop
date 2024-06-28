package com.example.baekshop.Domain.User.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "token")
public class Token {       // refresh token을 db에 저장할 token 엔티티

    @Id
    private String email;

    private String token;
}
