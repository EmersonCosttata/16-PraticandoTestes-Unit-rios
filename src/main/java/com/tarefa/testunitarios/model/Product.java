package com.tarefa.testunitarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer quant;
// MOdelo do produto com ds dados, Notação Entity faz com q ele seja uma entidade
//uma tabela, data gera os getters and setters . notnull faz com q n seja nulo os valores
}
