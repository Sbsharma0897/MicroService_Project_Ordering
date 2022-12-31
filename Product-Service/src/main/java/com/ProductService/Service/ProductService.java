package com.ProductService.Service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ProductService.Dto.ProductRequest;
import com.ProductService.Dto.ProductResponse;
import com.ProductService.Model.Product;
import com.ProductService.Repo.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
        		.name(productRequest.getName())
        		.price(productRequest.getPrice())
        		.description(productRequest.getDescription())
        		.build();
                

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}