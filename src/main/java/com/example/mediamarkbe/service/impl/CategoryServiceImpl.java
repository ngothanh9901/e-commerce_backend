package com.example.mediamarkbe.service.impl;
import com.example.mediamarkbe.dto.filter.FilteringCategoryDTO;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Category;
import com.example.mediamarkbe.respository.CategoryRepository;
import com.example.mediamarkbe.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public ResponseObject<Category> findCategory(FilteringCategoryDTO payload, Pageable pageable) {
        Page<Category> data = categoryRepository.findCategories(payload,pageable);
        ResponseObject<Category> responseObject = new ResponseObject<>(data.getContent(),data.getNumber(),data.getSize(),
                data.getTotalElements(),data.getTotalPages(),data.isLast());

        return responseObject;
    }

    @Override
    public Category add(Category payload, Long id) {
        Category category = id!=null ? categoryRepository.findById(id).get():new Category();
        category = payload.convertToModel(category);

        categoryRepository.save(category);
        return category;
    }

}
