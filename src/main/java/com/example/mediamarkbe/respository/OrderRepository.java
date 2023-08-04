package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Orders;
import com.example.mediamarkbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    boolean existsByStatusAndUser(boolean status, User user);
    Orders findByStatusAndUser(boolean status,User user);
//    List<Orders> findByStatusAndUser(boolean status, User user);
    List<Orders> findByUserId(Long id);
}

