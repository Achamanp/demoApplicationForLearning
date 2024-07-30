package com.E_CommerceApplication.controller;

import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.BrandDto;
import com.E_CommerceApplication.impl.BrandServiceImpl;
import com.E_CommerceApplication.pagination.BrandPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandServiceImpl;

    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandDto brandDto) {
        BrandDto createdBrand = brandServiceImpl.createBrand(brandDto);
        return ResponseEntity.ok(createdBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDto> updateBrand(@RequestBody BrandDto brandDto, @PathVariable Integer id) {
        BrandDto updatedBrand = brandServiceImpl.updateBrand(brandDto, id);
        return ResponseEntity.ok(updatedBrand);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Integer id) {
        BrandDto brandDto = brandServiceImpl.getBrandById(id);
        return ResponseEntity.ok(brandDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBrand(@PathVariable Integer id) {
        brandServiceImpl.deleteBrand(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Brand RemovedSuccessfully", true),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BrandPageResponse> getAllBrands(
    		@RequestParam(value = "pageSize", defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir) {
        BrandPageResponse response = brandServiceImpl.getAllBrands(pageSize, pageNumber, sortBy, sortDir);
        return ResponseEntity.ok(response);
    }
}
