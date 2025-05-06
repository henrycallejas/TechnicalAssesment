package com.test.Technical.Assesment.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.Technical.Assesment.dto.ProductDto;
import com.test.Technical.Assesment.model.Product;
import com.test.Technical.Assesment.repository.ProductRepository;
import com.test.Technical.Assesment.utils.Request;

@Service
public class ProductServiceImp implements ProductService {

    private static final String API_URL = "https://fakestoreapi.com/products";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            StringBuilder content = Request.sendRequest(API_URL);
            return objectMapper.readValue(
                    content.toString(), new TypeReference<List<ProductDto>>() {
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al llamar la API: " + e.getMessage(), e);
        }
    }

    @Override
    public ProductDto getProductById(Long id) {
        String findById = "";
        findById = API_URL + "/" + id;
        try {
            StringBuilder content = Request.sendRequest(findById);
            return objectMapper.readValue(
                    content.toString(), new TypeReference<ProductDto>() {
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al llamar la API: " + e.getMessage(), e);
        }
    }

    @Override
    public Product createProduct(ProductDto product) {
        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setImage(product.getImage());
        newProduct.setName(product.getTitle());
        newProduct.setPrice(product.getPrice());
        return this.productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(ProductDto order) {
        Optional<Product> product = this.productRepository.findById(order.getId());
        if(product.isPresent()){
            Product foundProduct = product.get();
            foundProduct.setDescription(order.getDescription());
            foundProduct.setImage(order.getImage());
            foundProduct.setName(order.getTitle());
            foundProduct.setPrice(order.getPrice());
            return this.productRepository.save(foundProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

}
