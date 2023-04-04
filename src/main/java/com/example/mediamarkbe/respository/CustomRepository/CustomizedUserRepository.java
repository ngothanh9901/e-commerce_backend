package com.example.mediamarkbe.respository.CustomRepository;

import com.example.mediamarkbe.dto.filter.FilteringUserDTO;
import com.example.mediamarkbe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomizedUserRepository {
    Page<User> findUser(FilteringUserDTO payload, Pageable pageable);
}
