package com.nawid.onlineshop.repo;

import com.nawid.onlineshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findByUserUsername(String username);

}
