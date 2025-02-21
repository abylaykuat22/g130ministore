package com.example.g130ministore.service;

import com.example.g130ministore.dto.BrandCreateRequest;
import com.example.g130ministore.entity.Brand;
import com.example.g130ministore.exception.EntityNotFoundException;
import com.example.g130ministore.exception.EntityUniqueException;
import com.example.g130ministore.repository.BrandRepository;
import com.example.g130ministore.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @InjectMocks
    private BrandServiceImpl brandService;
    @Mock
    private BrandRepository brandRepository;

    @Test
    void getBrandById_success() {
        long id = -1L;
        Mockito.when(brandRepository.findById(id)).thenReturn(Optional.of(Brand.builder().id(id).name("APPLE").code("APL").build()));
        Assertions.assertNotNull(brandService.getBrandById(id));
        Assertions.assertEquals("APPLE", brandService.getBrandById(id).getName());
        Assertions.assertEquals("APL", brandService.getBrandById(id).getCode());
    }

    @Test
    void getBrandById_fail() {
        long id = -1L;
        Mockito.when(brandRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> brandService.getBrandById(id));
    }

    @Test
    void createBrand_success() {
        BrandCreateRequest request = BrandCreateRequest.builder().name("APPLE").code("APL").build();
        Mockito.when(brandRepository.findByCode(request.getCode())).thenReturn(Optional.empty());
        Assertions.assertDoesNotThrow(() -> brandService.createBrand(request));
        Mockito.verify(brandRepository, Mockito.times(1)).save(Mockito.any(Brand.class));
    }

    @Test
    void createBrand_fail() {
        BrandCreateRequest request = BrandCreateRequest.builder().name("APPLE").code("APL").build();
        Mockito.when(brandRepository.findByCode(request.getCode())).thenReturn(Optional.of(Mockito.mock(Brand.class)));
        Assertions.assertThrows(EntityUniqueException.class, () -> brandService.createBrand(request));
    }


}
