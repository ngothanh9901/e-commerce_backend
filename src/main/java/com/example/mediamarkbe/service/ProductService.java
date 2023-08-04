package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.ProductResponse;
import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.dto.payload.ProductPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    @Cacheable(value = "product",key="{#pageable.pageNumber,#pageable.pageSize}",condition = "#payload.isNull()==true")
    ResponseObject<ProductResponse> findProduct(FilterProductDTO payload, Pageable pageable);

    Product add(ProductPayload payload,Long id);
    ProductResponse mapToDTO (Product product);
    ProductResponse getProductDetail(Long id);

}
