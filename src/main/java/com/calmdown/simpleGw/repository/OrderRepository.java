package com.calmdown.simpleGw.repository;

import com.calmdown.simpleGw.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String>{

    List<Orders> findByPhone(String phone);
}
