package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.CartResponse;
import com.example.mediamarkbe.dto.payload.AddingToCartPayload;
import com.example.mediamarkbe.dto.payload.UpdateCartPayload;

public interface OrderService {
    CartResponse addToCart(AddingToCartPayload payload);
    CartResponse getCart(Long id);
    CartResponse updateCart(UpdateCartPayload payload, Long userId);
    CartResponse deleteCart(Long productCartId, Long userId);
}
