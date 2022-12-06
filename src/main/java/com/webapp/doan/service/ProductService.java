package com.webapp.doan.service;

import com.webapp.doan.exceptions.EtAuthException;
import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product) throws EtBadRequestException;
    public Page<Product> findAllProducts(int page, int size) throws EtResourceNotFoundException;
    public Product findProductById(Integer id) throws EtResourceNotFoundException;
    public List<Product> findProductsByBrand(Integer brandId) throws EtResourceNotFoundException;
    public List<Product> findProductsByName(String name) throws EtResourceNotFoundException;
    public Integer deleteProductById(Integer id) throws EtAuthException;
    public Integer updateProductById(Product product, Integer id) throws EtAuthException;

}
