package com.E_CommerceApplication.impl;

import com.E_CommerceApplication.entity.Brand;
import com.E_CommerceApplication.exception.ResourceNotFoundException;
import com.E_CommerceApplication.payloads.BrandDto;
import com.E_CommerceApplication.pagination.BrandPageResponse;
import com.E_CommerceApplication.repository.BrandRepository;
import com.E_CommerceApplication.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        Brand brand = modelMapper.map(brandDto, Brand.class);
        Brand savedBrand = brandRepository.save(brand);
        return modelMapper.map(savedBrand, BrandDto.class);
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto, Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));
        brand.setName(brandDto.getName());
        brand.setDescription(brandDto.getDescription());
        Brand updatedBrand = brandRepository.save(brand);
        return modelMapper.map(updatedBrand, BrandDto.class);
    }

    @Override
    public BrandDto getBrandById(Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));
        return modelMapper.map(brand, BrandDto.class);
    }

    @Override
    public void deleteBrand(Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));
        brandRepository.delete(brand);
    }

    @Override
    public BrandPageResponse getAllBrands(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy);
        sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Brand> page = brandRepository.findAll(pageable);

        List<BrandDto> dtos = page.getContent().stream()
                .map(brand -> modelMapper.map(brand, BrandDto.class))
                .collect(Collectors.toList());

        BrandPageResponse response = new BrandPageResponse();
        response.setContent(dtos);
        response.setLastpage(page.isLast());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());

        return response;
    }
}
