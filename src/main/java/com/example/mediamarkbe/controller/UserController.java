package com.example.mediamarkbe.controller;

import com.example.mediamarkbe.dto.UserResponse;
import com.example.mediamarkbe.dto.filter.FilteringUserDTO;
import com.example.mediamarkbe.dto.payload.RolePayload;
import com.example.mediamarkbe.dto.payload.UserPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Role;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseObject<UserResponse> getUser(FilteringUserDTO payload, Pageable pageable){
        return userService.findingUser(payload,pageable);
    }
    

}
