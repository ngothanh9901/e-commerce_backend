package com.example.mediamarkbe.respository.CustomRepository;

import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.dto.filter.FilteringCategoryDTO;
import com.example.mediamarkbe.model.Category;
import com.example.mediamarkbe.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizedCategoryRepository {
    Page<Category> findCategories(FilteringCategoryDTO payload, Pageable pageable);
}
