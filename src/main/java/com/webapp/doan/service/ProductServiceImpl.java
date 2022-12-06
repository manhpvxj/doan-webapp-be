package com.webapp.doan.service;


import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.Product;
import com.webapp.doan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepo;

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Page<Product> findAllProducts(int page, int size) {
        Pageable pageRender = PageRequest.of(page, size);
        return productRepo.findAll(pageRender);
    }

    @Override
    public Product findProductById(Integer id) {

        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> findProductsByBrand(Integer brandId) throws EtResourceNotFoundException {
        return productRepo.findByCategory_Id(brandId);
    }

    @Override
    public List<Product> findProductsByName(String name) throws EtResourceNotFoundException {
        return productRepo.findAllByName(name);
    }

    @Override
    public Integer deleteProductById(Integer id) {

        productRepo.deleteById(id);
        return 1;
    }

    @Override
    public Integer updateProductById(Product product, Integer id) {

        Optional<Product> currentProduct = productRepo.findById(id);
        if(currentProduct.isPresent()) {
            return null;
        }
        productRepo.save(product);

        return product.getId();
    }
}
