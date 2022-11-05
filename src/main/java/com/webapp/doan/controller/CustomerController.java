package com.webapp.doan.controller;

import com.webapp.doan.model.Category;
import com.webapp.doan.model.Product;
import com.webapp.doan.service.CategoryService;
import com.webapp.doan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<Map<String, List<Product>>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        Map<String, List<Product>> map = new HashMap<String, List<Product>>();
        map.put("data", products);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Map<String, Product>> FindById(@PathVariable("id") Integer id) {
        Product product = productService.findProductById(id);
        Map<String, Product> map = new HashMap<>();
        map.put("data", product);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<String, List<Category>>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        Map<String, List<Category>> map = new HashMap<String, List<Category>>();
        map.put("data", categories);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
