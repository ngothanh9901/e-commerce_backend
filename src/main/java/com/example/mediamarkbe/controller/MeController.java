package com.example.mediamarkbe.controller;


import com.example.mediamarkbe.common.enumeration.exception.ResourceNotFoundException;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.respository.UserRepository;
import com.example.mediamarkbe.security.UserPrincipal;
import com.example.mediamarkbe.security.oath2.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
