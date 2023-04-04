package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.UserResponse;
import com.example.mediamarkbe.dto.filter.FilteringUserDTO;
import com.example.mediamarkbe.dto.payload.RolePayload;
import com.example.mediamarkbe.dto.payload.UserPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Role;
import com.example.mediamarkbe.model.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User saveUser(UserPayload payload);
    Role saveRole(RolePayload payload);
    void addRoleToUser(String username, String roleName);
    ResponseObject<UserResponse> findingUser(FilteringUserDTO payload, Pageable pageable);
}
