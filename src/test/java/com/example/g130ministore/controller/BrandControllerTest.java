package com.example.g130ministore.controller;

import com.example.g130ministore.dto.BrandCreateRequest;
import com.example.g130ministore.dto.BrandDto;
import com.example.g130ministore.exception.EntityNotFoundException;
import com.example.g130ministore.exception.EntityUniqueException;
import com.example.g130ministore.service.BrandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BrandController.class)
class BrandControllerTest {

    @MockitoBean
    private BrandService brandService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getBrandById_notFound() throws Exception {
        Mockito.when(brandService.getBrandById(-1L)).thenThrow(new EntityNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/brands/-1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getBrandById_success() throws Exception {
        Mockito.when(brandService.getBrandById(-1L)).thenReturn(Mockito.mock(BrandDto.class));
        mockMvc.perform(MockMvcRequestBuilders.get("/brands/-1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createBrand_success() throws Exception {
        BrandCreateRequest request = BrandCreateRequest.builder().name("APPLE").code("APL").build();
        String requestJson = objectMapper.writeValueAsString(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(brandService, Mockito.times(1)).createBrand(request);
    }

    @Test
    public void createBrand_fail() throws Exception {
        BrandCreateRequest request = BrandCreateRequest.builder().name("APPLE").code("APL").build();
        String requestJson = objectMapper.writeValueAsString(request);
        Mockito.doThrow(new EntityUniqueException()).when(brandService).createBrand(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
