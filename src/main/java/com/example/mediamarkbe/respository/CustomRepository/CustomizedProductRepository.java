package com.example.mediamarkbe.respository.CustomRepository;

import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomizedProductRepository {
    Page<Product> findProduct(FilterProductDTO payload, Pageable pageable);
}
