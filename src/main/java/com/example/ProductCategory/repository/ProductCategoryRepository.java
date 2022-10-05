package com.example.ProductCategory.repository;

import com.example.ProductCategory.domain.dao.ProductCategoryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryDao, String> {
}
