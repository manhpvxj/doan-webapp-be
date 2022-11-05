package com.webapp.doan.service;


import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.Product;
import com.webapp.doan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> findAllProducts() {

        return productRepo.findAll();
    }

    @Override
    public Product findProductById(Integer id) {

        return productRepo.findById(id).get();
    }

    @Override
    public Iterable<Product> findProductByBrand(String brand) throws EtResourceNotFoundException {
        return productRepo.findByCategory(brand);
    }

    @Override
    public Iterable<Product> findProductByName(String name, int page, int size) throws EtResourceNotFoundException {
        return null;
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

        return product.getProductId();
    }
}
