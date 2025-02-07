package com.example.g130ministore.mapper;

import com.example.g130ministore.dto.BrandCreateRequest;
import com.example.g130ministore.dto.BrandDto;
import com.example.g130ministore.entity.Brand;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDto toDto(Brand entity);

    Brand toEntity(BrandDto dto);

    Brand toEntity(BrandCreateRequest dto);

    List<BrandDto> toDtoList(List<Brand> entities);
}
