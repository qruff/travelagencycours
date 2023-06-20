package com.travel.travel.dao;

import com.travel.travel.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
    List<Cart> findByExploiter_id(int exploiterId);
}
