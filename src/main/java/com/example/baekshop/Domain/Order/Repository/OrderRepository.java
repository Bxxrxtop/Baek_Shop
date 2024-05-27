package com.example.baekshop.Domain.Order.Repository;


import com.example.baekshop.Domain.Order.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o " +
            "where o.user.email = :email " +
            "order by o.orderDate desc")
    List<Order> findOrders(@Param("email") String email);// 현재 로그인한 사용자의 주문 데이터를 조회함 (위의 조건에 맞춰서)

    @Query("select count(o) from Order o " +
            "where o.user.email = :email")
    Long countOrder(@Param("email") String email); // 현재 로그인한 사용자의 주문 개수를 조회함 (위의 조건에 맞춰서)
}
