package com.example.eshoptestapi.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eshoptestapi.entity.Product;
import com.example.eshoptestapi.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product getProductById(Long id) {
        return productRepository.getProductbyID(id);
    }
    public List<Product> getProductByBrandId(Long brandId) {
        return productRepository.findByBrandId(brandId);
    }
    public List<Product> getProductByCategoriesId(Long categoriesId) {
        return productRepository.findByCategoriesId(categoriesId);
    }
    public List<Product> getProductByBrandIdAndCategoriesId(Long brandId, Long categoriesId) {
        return productRepository.findByBrandIdAndCategoriesId(brandId, categoriesId);
    }
    //create product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    //update product
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    //delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    //get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
}
