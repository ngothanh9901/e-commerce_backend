package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.RolePayload;
import com.example.mediamarkbe.dto.UserPayload;
import com.example.mediamarkbe.model.Role;
import com.example.mediamarkbe.model.User;

public interface UserService {
    User saveUser(UserPayload payload);
    Role saveRole(RolePayload payload);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
}
