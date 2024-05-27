package com.example.baekshop.Domain.Item.Repository;

import com.example.baekshop.Domain.Item.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
