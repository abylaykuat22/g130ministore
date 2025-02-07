package com.example.g130ministore.service;

import com.example.g130ministore.dto.BrandCreateRequest;
import com.example.g130ministore.dto.BrandDto;
import com.example.g130ministore.entity.Brand;

import java.util.List;

public interface BrandService {

    List<BrandDto> getBrands(String name, String code, String description);

    BrandDto getBrandById(Long id);

    void createBrand(BrandCreateRequest request);

    void editBrand(BrandDto brandDto);

    void deleteBrandById(Long id);
}
