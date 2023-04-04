package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Product;
import com.example.mediamarkbe.respository.CustomRepository.CustomizedProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>, CustomizedProductRepository {
}
