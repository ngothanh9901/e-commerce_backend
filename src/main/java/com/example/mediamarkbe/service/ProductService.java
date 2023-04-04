package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.ProductResponse;
import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.dto.payload.ProductPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Product;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ResponseObject<ProductResponse> findProduct(FilterProductDTO payload, Pageable pageable);
    Product add(ProductPayload payload,Long id);
}
