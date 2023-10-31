package com.baqalati.product.catalog.repository;

import com.baqalati.product.catalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom query methods or additional repository methods can be defined here
}
