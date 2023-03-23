package com.example.mediamarkbe.dto;

import com.example.mediamarkbe.model.Role;
import com.example.mediamarkbe.model.User;
import lombok.Data;

@Data
public class RolePayload {
    private String name;
    private String code;

    public static Role convertToModel(RolePayload payload){
        Role role = new Role();

        role.setName(payload.getName());
        role.setCode(payload.getCode());

        return role;
    }
}
