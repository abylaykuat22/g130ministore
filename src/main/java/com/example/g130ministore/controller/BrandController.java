package com.example.g130ministore.controller;

import com.example.g130ministore.dto.BrandCreateRequest;
import com.example.g130ministore.dto.BrandDto;
import com.example.g130ministore.entity.Brand;
import com.example.g130ministore.exception.EntityNotFoundException;
import com.example.g130ministore.exception.EntityUniqueException;
import com.example.g130ministore.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brands")
@RequiredArgsConstructor
@Tag(name = "BrandController", description = "API для управления брендами")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    @Operation(summary = "Получение списка брендов", description = "Возвращает список брендов по указанным параметрам. Если параметров входных нет - возвращает список всех брендов")
    public ResponseEntity<List<BrandDto>> getBrands(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String description
    ) {
        try {
            List<BrandDto> brands = brandService.getBrands(name, code, description);
            return ResponseEntity.ok(brands);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение бренда по ID", description = "Возвращает бренд по указанному ID или возвращает статус 404 если бренд не найден")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Бренд не найден"),
            @ApiResponse(responseCode = "200", description = "Бренды получены успешно", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BrandDto.class))
            })
    })
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Long id) {
        try {
            BrandDto brand = brandService.getBrandById(id);
            return new ResponseEntity<>(brand, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @Operation(summary = "Создание бренда", description = "Создает бренд проверив на уникальность кода")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Неверный запрос"),
            @ApiResponse(responseCode = "201", description = "Бренд успешно создан")
    })
    public ResponseEntity<Void> createBrand(@RequestBody BrandCreateRequest request) {
        try {
            brandService.createBrand(request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EntityUniqueException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    @Operation(summary = "Изменение бренда", description = "Изменить бренд проверив на уникальность")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Неверный запрос"),
            @ApiResponse(responseCode = "404", description = "Бренд не найден"),
            @ApiResponse(responseCode = "200", description = "Бренд успешно изменен")
    })
    public ResponseEntity<Void> editBrand(@RequestBody BrandDto brandDto) {
        try {
            brandService.editBrand(brandDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (EntityUniqueException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление бренда по ID", description = "Удалить бренд по ID проверив на наличие")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Бренд не найден"),
            @ApiResponse(responseCode = "204", description = "Бренд успешно удален")
    })
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        try {
            brandService.deleteBrandById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
