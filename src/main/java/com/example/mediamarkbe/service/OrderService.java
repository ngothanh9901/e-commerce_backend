package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.CartResponse;
import com.example.mediamarkbe.dto.ProductResponse;
import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.dto.payload.AddingToCartPayload;
import com.example.mediamarkbe.dto.payload.UpdateCartPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    CartResponse addToCart(AddingToCartPayload payload,Long userId);
    CartResponse getCart(Long id);
    CartResponse updateCart(UpdateCartPayload payload, Long userId);
    CartResponse deleteCart(Long productCartId, Long userId);

    ResponseObject<CartResponse> findOrder(Pageable pageable);
}
