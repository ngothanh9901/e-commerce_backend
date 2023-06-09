package com.example.mediamarkbe.controller;

import com.example.mediamarkbe.dto.CartResponse;
import com.example.mediamarkbe.dto.OrderResponse;
import com.example.mediamarkbe.dto.payload.AddingToCartPayload;
import com.example.mediamarkbe.dto.payload.UpdateCartPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.security.UserPrincipal;
import com.example.mediamarkbe.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<String> addToCart (@RequestBody AddingToCartPayload payload){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        orderService.addToCart(payload,userPrincipal.getUser().getId());
        return ResponseEntity.ok("Add success");
    }

    @GetMapping("/cart")
    public CartResponse getCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return orderService.getCart(userPrincipal.getUser().getId());
    }
    @PutMapping("/cart")
    public CartResponse updateCart(@RequestBody UpdateCartPayload payload){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return orderService.updateCart(payload,userPrincipal.getUser().getId());
    }
    @DeleteMapping("/cart/{id}")
    public CartResponse deleteCart(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return orderService.deleteCart(id, userPrincipal.getUser().getId());
    }

    @GetMapping
    public ResponseObject<OrderResponse> getOrder(Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return null;

    }

}
