package com.example.mediamarkbe.controller;

import com.example.mediamarkbe.dto.filter.FilteringCategoryDTO;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Category;
import com.example.mediamarkbe.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseObject<Category> getCategorys(FilteringCategoryDTO oayload, Pageable pageable){
        return categoryService.findCategory(oayload,pageable);
    }
    @PostMapping("/")
    public ResponseEntity<Category> add(@RequestBody Category payload){
        Category category = categoryService.add(payload,null);
        return ResponseEntity.ok(category);
    }
    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category payload){
        Category category = categoryService.add(payload,payload.getId());
        return ResponseEntity.ok(category);
    }
}
