package com.webapp.doan.service;

import com.webapp.doan.dto.ProductDto;
import com.webapp.doan.exceptions.EtAuthException;
import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product createProduct(ProductDto product) throws EtBadRequestException;
    public Page<Product> findAllProductsByPage(int page, int size) throws EtResourceNotFoundException;
    public Product findProductById(Integer id) throws EtResourceNotFoundException;
    public List<Product> findAllProducts() throws EtResourceNotFoundException;
    public List<Product> findProductsByBrandAndName(Integer brandId, String name) throws EtResourceNotFoundException;
    public List<Product> findProductsByName(String name) throws EtResourceNotFoundException;
    public Integer deleteProductById(Integer id) throws EtAuthException;
    public Product editProductById(Integer id, ProductDto product) throws EtAuthException;

}
