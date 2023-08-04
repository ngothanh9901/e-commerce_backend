package com.example.mediamarkbe.controller;

import com.example.mediamarkbe.dto.ProductResponse;
import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.dto.payload.ProductPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Product;
import com.example.mediamarkbe.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseObject<ProductResponse> getProducts(FilterProductDTO payload, Pageable pageable){
        return productService.findProduct(payload,pageable);
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody ProductPayload payload){
        Product product = productService.add(payload,null);
        return ResponseEntity.ok(product);
    }
    @PutMapping
    public ResponseEntity<Product> update(@RequestBody ProductPayload payload){
        Product product = productService.add(payload, payload.getId());
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductDetail(@PathVariable("id") Long id){
        return productService.getProductDetail(id);
    }
}