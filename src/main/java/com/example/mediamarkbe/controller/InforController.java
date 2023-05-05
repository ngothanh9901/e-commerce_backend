package com.example.mediamarkbe.controller;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class InforController {

    @GetMapping("/thu")
    public String getInfo() {
        return "ngoducthanh";
    }


}
