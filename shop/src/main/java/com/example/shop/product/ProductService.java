package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    @Transactional
    public Long createProduct(ProductCreateRequest request){
        Product existingProduct = productRepository.findByProductId(request.getProductName());
        if(existingProduct == null) {
            throw new RuntimeException("이미 존재하는 상품명 입니다"+request.getProductName());
        }
        //
        Product product = new Product(
                request.getProductName()
        );
        productRepository.save(product);

        return product.getId();
    }

    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    @Transactional
    public Product getProductByName(String productName){
        Product product = productRepository.findByProductId(productName);
        if(product == null){
            throw new RuntimeException("존재하지 않는 상품입니다"+productName);
        }
        return product;
    }

    @Transactional
    public void updateProduct(String productName, ProductUpdateRequest request){
        Product product = productRepository.findByProductId(productName);
        if(product == null){
            throw new RuntimeException("존재하지 않는 상품입니다"+productName);
        }
        product.updateInfo(request.getProductName());

    }

    public void deleteProduct(Long productId){
        Product product = productRepository.findById(productId);
        if (product == null){
            throw new RuntimeException("회원을 찾을 수 없습니다");
        }
        productRepository.deleteById(productId);
    }



}