package com.example.mediamarkbe.service;

import com.example.mediamarkbe.dto.filter.FilteringCategoryDTO;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Category;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    ResponseObject<Category> findCategory(FilteringCategoryDTO payload, Pageable pageable);
    Category add(Category payload, Long id);
}
