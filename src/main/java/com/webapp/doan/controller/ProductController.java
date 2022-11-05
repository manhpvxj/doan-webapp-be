package com.webapp.doan.controller;

import com.webapp.doan.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webapp.doan.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Product>> CreateProduct(Product payload) {
        Product product = productService.createProduct(payload);
        Map<String, Product> map = new HashMap<>();
        map.put("data", product);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> DeleteProductById(@PathVariable("id") Integer id) {
        Integer productId = productService.deleteProductById(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", "Delete productId: " + productId + " successful");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
