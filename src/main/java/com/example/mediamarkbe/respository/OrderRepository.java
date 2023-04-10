package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Orders;
import com.example.mediamarkbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    boolean existsByStatusAndUser(boolean status, User user);
    Orders findByStatusAndUser(boolean status,User user);
}

