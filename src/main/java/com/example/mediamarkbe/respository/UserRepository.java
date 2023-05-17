package com.example.mediamarkbe.respository;


import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.respository.CustomRepository.CustomizedUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomizedUserRepository {
    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByEmail(String email);
}
