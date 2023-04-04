package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
