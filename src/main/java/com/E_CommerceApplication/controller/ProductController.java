package com.E_CommerceApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.E_CommerceApplication.impl.ProductServiceImpl;
import com.E_CommerceApplication.pagination.ProductPageResponse;
import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.ProductDto;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        ProductDto createProductDto = productServiceImpl.createProduct(productDto, id);
        return new ResponseEntity<>(createProductDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        ProductDto updatedProduct = productServiceImpl.updateProduct(productDto, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
        ProductDto productDto = productServiceImpl.getProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<ProductPageResponse> getAllProducts(
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ProductPageResponse productPageResponse = productServiceImpl.GetAllProduct(pageNumber, pageSize, sortDir, sortBy);
        return ResponseEntity.ok(productPageResponse);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ProductPageResponse> getProductsByCategoryId(
        @PathVariable Integer id,
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        ProductPageResponse productPageResponse = productServiceImpl.getProductByCategoryId(id, pageNumber, pageSize, sortDir, sortBy);
        return ResponseEntity.ok(productPageResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id) {
       this.productServiceImpl.deleteProduct(id);
       return new ResponseEntity<ApiResponse>(new ApiResponse("Product Deleted Successfully", true),HttpStatus.OK);
    }
}
