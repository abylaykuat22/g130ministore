package com.example.g130ministore.service.impl;

import com.example.g130ministore.dto.BrandCreateRequest;
import com.example.g130ministore.dto.BrandDto;
import com.example.g130ministore.entity.Brand;
import com.example.g130ministore.exception.EntityNotFoundException;
import com.example.g130ministore.exception.EntityUniqueException;
import com.example.g130ministore.mapper.BrandMapper;
import com.example.g130ministore.repository.BrandRepository;
import com.example.g130ministore.service.BrandService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    @PersistenceContext
    private EntityManager entityManager;
    private final BrandRepository brandRepository;

    @Override
    public List<BrandDto> getBrands(String name, String code, String description) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
        Root<Brand> root = cq.from(Brand.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.equal(cb.lower(root.get("name")), name.toLowerCase()));
        }

        if (code != null && !code.isEmpty()) {
            predicates.add(cb.equal(cb.lower(root.get("code")), code.toLowerCase()));
        }

        if (description != null && !description.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        List<Brand> brands = entityManager.createQuery(cq).getResultList();
        return BrandMapper.INSTANCE.toDtoList(brands);
    }

    @Override
    public BrandDto getBrandById(Long id) {
        return brandRepository.findById(id)
                .map(brand -> BrandMapper.INSTANCE.toDto(brand))
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
    }

    private void checkCodeUnique(String code, Long brandId) {
        brandRepository.findByCode(code)
                .filter(entity -> !Objects.equals(brandId, entity.getId()))
                .ifPresent(brand -> {
                    throw new EntityUniqueException("Brand already exist");
                });
    }

    @Override
    public void createBrand(BrandCreateRequest request) {
        checkCodeUnique(request.getCode(), null);

        Brand brand = BrandMapper.INSTANCE.toEntity(request);
        brandRepository.save(brand);
    }

    @Override
    public void editBrand(BrandDto brandDto) {
        // Валидация полуй
        getBrandById(brandDto.getId());
        checkCodeUnique(brandDto.getCode(), brandDto.getId());

        Brand brand = BrandMapper.INSTANCE.toEntity(brandDto);
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
        // Проверка на наличие бренда по ID
        getBrandById(id);

        brandRepository.deleteById(id);
    }
}
