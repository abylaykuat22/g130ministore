package com.example.g130ministore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "DTO для работы с категориями")
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
}
