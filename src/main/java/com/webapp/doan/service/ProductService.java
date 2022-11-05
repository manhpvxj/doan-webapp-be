package com.webapp.doan.service;

import com.webapp.doan.exceptions.EtAuthException;
import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product) throws EtBadRequestException;
    public List<Product> findAllProducts() throws EtResourceNotFoundException;
    public Product findProductById(Integer id) throws EtResourceNotFoundException;
    public Iterable<Product> findProductByBrand(String brand) throws EtResourceNotFoundException;
    public Iterable<Product> findProductByName(String name, int page, int size) throws EtResourceNotFoundException;
    public Integer deleteProductById(Integer id) throws EtAuthException;
    public Integer updateProductById(Product product, Integer id) throws EtAuthException;

}
