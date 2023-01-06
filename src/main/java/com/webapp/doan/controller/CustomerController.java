package com.webapp.doan.controller;

import com.webapp.doan.dto.InvoiceDto;
import com.webapp.doan.dto.PageDto;
import com.webapp.doan.dto.ProductDto;
import com.webapp.doan.model.Category;
import com.webapp.doan.model.Invoice;
import com.webapp.doan.model.Product;
import com.webapp.doan.service.CategoryService;
import com.webapp.doan.service.InvoiceService;
import com.webapp.doan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestParam(name = "category", required = false) Integer categoryId,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "8") int size) {
        if(categoryId == -1 && search == "") {
            List<Product> allProducts = productService.findAllProducts();
            PageDto pageStatus = new PageDto(allProducts.size(), page, size);
            Page<Product> products = productService.findAllProductsByPage(page, size);
            List<Product> listProducts = products.getContent();
            Map<String, Object> map1 = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            map1.put("data", listProducts);
            map1.put("total", pageStatus);
            map.put("data", map1);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        if (categoryId == -1 && search != "") {
            List<Product> products = productService.findProductsByName(search);
            PageDto pageStatus = new PageDto(products.size(), page, size);
            Map<String, Object> map1 = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            map1.put("data", products);
            map1.put("total", pageStatus);
            map.put("data", map1);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            List<Product> products = productService.findProductsByBrandAndName( categoryId, search);
            PageDto pageStatus = new PageDto(products.size(), page, size);
            Map<String, Object> map1 = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            map1.put("data", products);
            map1.put("total", pageStatus);
            map.put("data", map1);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
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

    @PostMapping("/invoices/create")
    public ResponseEntity<Map<String, Invoice>> CreateInvoice(@RequestBody InvoiceDto payload) {
        Invoice invoice = invoiceService.createInvoice(payload);
        Map<String, Invoice> map = new HashMap<>();
        map.put("data", invoice);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
