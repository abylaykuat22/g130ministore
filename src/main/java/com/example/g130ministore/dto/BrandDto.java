package com.example.g130ministore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "DTO для работы с брендами")
public class BrandDto {

    private Long id;
    private String name;
    private String code;
    private String description;
    private List<CategoryDto> categories;
}
