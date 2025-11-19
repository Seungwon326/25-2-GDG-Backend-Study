package com.example.shop.product;


import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductCreateRequest request){
        Long productId = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/product"+productId)).build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProduct(
            @PathVariable String productName){

        Product product = productService.getProductByName(productName);
        return ResponseEntity.ok(product);
    }


    @PatchMapping("/{productName}")
    public ResponseEntity<Void> updateProduct(
            @RequestBody ProductUpdateRequest request,
            @PathVariable String productName){
        productService.updateProduct(productName,request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productName}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productName){

        productService.deleteProduct(productName);
        return ResponseEntity.ok().build();
    }



}