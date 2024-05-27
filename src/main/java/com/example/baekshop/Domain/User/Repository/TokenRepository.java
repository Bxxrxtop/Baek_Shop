package com.example.baekshop.Domain.User.Repository;

import com.example.baekshop.Domain.User.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByEmail(String email);

}
