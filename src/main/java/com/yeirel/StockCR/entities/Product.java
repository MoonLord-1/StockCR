package com.yeirel.StockCR.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long codeProducto;

    private  String name;
    private BigDecimal price;
    private  int quantityStock;
    private  String imageURL;
}
