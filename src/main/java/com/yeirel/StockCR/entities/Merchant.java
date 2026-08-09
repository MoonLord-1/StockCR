package com.yeirel.StockCR.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private long id;

    private String businessName;

    private String slug;

    private String email;

    private String passwordHash;

    private boolean active = true;




}

