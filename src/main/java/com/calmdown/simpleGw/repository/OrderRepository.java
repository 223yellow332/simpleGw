package com.calmdown.simpleGw.repository;

import com.calmdown.simpleGw.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, String>{

}
