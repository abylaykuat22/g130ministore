package com.example.g130ministore.repository;

import com.example.g130ministore.entity.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.liquibase.enabled=false"
})
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;


    Brand newBrand = Brand.builder().name("APPLE").code("APL").build();


    @BeforeEach
    void setUp() {
        newBrand = brandRepository.save(newBrand);
    }

    @Test
    void findByCode_success() {
        Brand brand = brandRepository.findByCode(newBrand.getCode()).orElse(null);
        Assertions.assertNotNull(brand);
        Assertions.assertEquals(newBrand.getCode(), brand.getCode());
        Assertions.assertEquals(newBrand.getName(), brand.getName());
    }

    @Test
    void deleteBrand_success() {
        brandRepository.delete(newBrand);
        Brand brand = brandRepository.findById(newBrand.getId()).orElse(null);
        Assertions.assertNull(brand);
    }
}
