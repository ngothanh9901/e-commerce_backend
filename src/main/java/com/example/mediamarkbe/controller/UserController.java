package com.example.mediamarkbe.controller;

import com.example.mediamarkbe.dto.RolePayload;
import com.example.mediamarkbe.dto.UserPayload;
import com.example.mediamarkbe.model.Role;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserPayload payload){
        User user = userService.saveUser(payload);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/role")
    public ResponseEntity<Role> saveRole(@RequestBody RolePayload payload){
        Role role = userService.saveRole(payload);
        return ResponseEntity.ok().body(role);
    }

}
