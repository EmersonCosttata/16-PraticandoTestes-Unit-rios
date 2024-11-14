package com.tarefa.testunitarios.repository;

import com.tarefa.testunitarios.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Classe responsavel por manipular o produto!
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}