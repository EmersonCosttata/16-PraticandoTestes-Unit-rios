package com.tarefa.testunitarios.service;
import com.tarefa.testunitarios.model.Product;
import com.tarefa.testunitarios.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    // cria o produto, validando q n pode ser nulo, preço negativo e outras validações
    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        return productRepository.save(product);
    }
        // lista todos os produtos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //busca por id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    // faz update do produto, vendo se ele existe primeiro, dps valida os campos e salva novamente!
    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (productDetails.getName() != null) {
            product.setName(productDetails.getName());
        }
        if (productDetails.getDescription() != null) {
            product.setDescription(productDetails.getDescription());
        }
        if (productDetails.getPrice() != null && productDetails.getPrice() > 0) {
            product.setPrice(productDetails.getPrice());
        }
        if (productDetails.getQuant() != null) {
            product.setQuant(productDetails.getQuant());
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
