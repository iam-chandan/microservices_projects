package com.ecommerce.productService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.productService.entity.CategorieEntity;

@Repository
public interface CategoriesRepository extends JpaRepository<CategorieEntity, Integer>{

	Optional<CategorieEntity> findByNameIgnoreCase(String categorie);

}
