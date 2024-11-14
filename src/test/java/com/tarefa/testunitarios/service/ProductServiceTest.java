package com.tarefa.testunitarios.service;

import com.tarefa.testunitarios.model.Product;
import com.tarefa.testunitarios.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Product Test");
        product.setDescription("Test Description Show");
        product.setPrice(100.0);
        product.setQuant(10);
    }

    // Teste de CREATE
    @Test
    void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Product Test", createdProduct.getName());
        assertEquals(100.0, createdProduct.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    // Teste de READ
    @Test
    void testGetProductByIdFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Optional<Product> fetchedProduct = productService.getProductById(1L);

        assertTrue(fetchedProduct.isPresent());
        assertEquals("Product Test", fetchedProduct.get().getName());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Product> fetchedProduct = productService.getProductById(1L);

        assertFalse(fetchedProduct.isPresent());
    }

    // Teste de UPDATE
    @Test
    void testUpdateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        product.setPrice(120.0); // Atualizando o preço
        Product updatedProduct = productService.updateProduct(1L, product);

        assertNotNull(updatedProduct);
        assertEquals(120.0, updatedProduct.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    // Teste de DELETE
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
