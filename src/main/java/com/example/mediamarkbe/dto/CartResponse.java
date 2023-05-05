package com.example.mediamarkbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private List<ProductCartResponse> products;
    private Double sum;
    private Long idOrder;
}
