package com.stockmonitoringapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;

@Entity
@Table(name = "Portfolio")
@Data
@Builder
public class Portfolio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
}