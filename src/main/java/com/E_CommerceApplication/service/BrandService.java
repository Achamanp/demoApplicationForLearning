package com.E_CommerceApplication.service;

import com.E_CommerceApplication.payloads.BrandDto;
import com.E_CommerceApplication.pagination.BrandPageResponse;

public interface BrandService {
    public BrandDto createBrand(BrandDto brandDto);
    public BrandDto updateBrand(BrandDto brandDto, Integer id);
    public BrandDto getBrandById(Integer id);
    public void deleteBrand(Integer id);
    public BrandPageResponse getAllBrands(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);
}
