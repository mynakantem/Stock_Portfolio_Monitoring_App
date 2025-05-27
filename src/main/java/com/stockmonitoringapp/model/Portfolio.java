package com.stockmonitoringapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "portfolios")
public class Portfolio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "Portfolio name cannot be blank")
    @Size(min = 3, max = 100, message = "Portfolio name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", 
             message = "Portfolio name can only contain letters, numbers, spaces, hyphens, and underscores")
    private String name;
    
    public Portfolio() {
    }
    
    public Portfolio(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @PrePersist
    @PreUpdate
    public void normalizeName() {
        if (name != null) {
            name = name.replaceAll("\\s+", " ").trim();
        }
    }
}