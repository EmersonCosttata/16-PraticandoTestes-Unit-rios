package com.tarefa.testunitarios.controller;

import com.tarefa.testunitarios.model.Product;
import com.tarefa.testunitarios.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private Product product;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        product = new Product();
        product.setId(1L);
        product.setName("Product Test");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setQuant(10);
    }

    // Teste de CREATE
    @Test
    void testCreateProduct() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product Test\",\"description\":\"Test Description\",\"price\":100.0,\"quant\":10}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Product Test"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    // Teste de READ
    @Test
    void testGetProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product Test"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    // Teste de UPDATE
    @Test
    void testUpdateProduct() throws Exception {
        product.setPrice(120.0);
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(product);
        mockMvc.perform(put("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product Test Updated\",\"description\":\"Updated Description\",\"price\":120.0,\"quant\":15}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(120.0));
    }
    // Teste de DELETE
    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
