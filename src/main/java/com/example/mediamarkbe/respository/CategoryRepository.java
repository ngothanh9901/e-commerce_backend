package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Category;
import com.example.mediamarkbe.respository.CustomRepository.CustomizedCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> , CustomizedCategoryRepository {
}
