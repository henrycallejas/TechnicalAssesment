package com.test.Technical.Assesment.service;

import java.util.List;
import com.test.Technical.Assesment.model.Product;
import com.test.Technical.Assesment.model.ProductDto;

public interface ProductService {
List<ProductDto> getAllProducts();
ProductDto getProductById(Long id);
Product createProduct(ProductDto product);
Product updateProduct(ProductDto product);
void deleteProduct(Long id);
}
