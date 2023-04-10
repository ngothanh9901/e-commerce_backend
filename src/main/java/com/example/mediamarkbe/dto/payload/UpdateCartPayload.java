package com.example.mediamarkbe.dto.payload;

import lombok.Data;

@Data
public class UpdateCartPayload {
    private Long productCartId;
    private Long quantity;
}
