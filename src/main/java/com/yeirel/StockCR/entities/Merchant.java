package com.yeirel.StockCR.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "merchants")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(length = 150, nullable = false)
    private String businessName;

    @Column(unique = true, nullable = false,length = 150)
    private String slug;

    @Email
    @Column(unique = true, nullable = false,length = 150)
    private String email;

    @Column(nullable = false, length = 200)
    private String passwordHash;

    @Builder.Default
    private boolean active = true;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "merchant")
    @Builder.Default
    private List<StockMovement> stockMovements = new ArrayList<>();

}

