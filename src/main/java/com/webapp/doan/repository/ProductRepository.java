package com.webapp.doan.repository;

import com.webapp.doan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.category.id = :brandId")
    List<Product> findAllByCategory_IdAndName(Integer brandId, String name);
    List<Product> findByCategory_Id(Integer id);
    @Query(value = "SELECT * FROM Product WHERE name LIKE %:name%", nativeQuery = true)
    List<Product> findAllByName(String name);
}
