package com.webapp.doan.service;


import com.webapp.doan.dto.ProductDto;
import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.Category;
import com.webapp.doan.model.Product;
import com.webapp.doan.repository.CategoryRepository;
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
    private CategoryRepository categoryRepo;

    public ProductServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Product createProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepo.findById(productDto.getCategoryId());
        if(!category.isPresent()) {
           throw new EtBadRequestException("Invalid information");
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPriceSell(productDto.getPriceSell());
        product.setQuantity(productDto.getQuantity());
        product.setImages(productDto.getImages());
        product.setCover(productDto.getCover());
        product.setCategory(category.get());
        return productRepo.save(product);
    }

    @Override
    public Page<Product> findAllProductsByPage(int page, int size) {
        Pageable pageRender = PageRequest.of(page - 1, size);
        return productRepo.findAll(pageRender);
    }

    @Override
    public Product findProductById(Integer id) {

        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> findAllProducts() throws EtResourceNotFoundException {
        return productRepo.findAll();
    }

    public List<Product> findProductsByBrandAndName(Integer brandId, String name) throws EtResourceNotFoundException {
        return productRepo.findAllByCategory_IdAndName(brandId, name);
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
