package com.stockmonitoringapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PortfolioDTO {
    
    private Long id;
    
    @NotBlank(message = "Portfolio name is required")
    @Size(min = 3, max = 100, message = "Portfolio name must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-_]+$", 
             message = "Portfolio name can only contain letters, numbers, spaces, hyphens, and underscores")
    private String name;
    
    public PortfolioDTO() {
    }
    
    public PortfolioDTO(Long id, String name) {
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
    
    public static boolean isValidPortfolioName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        
        if (name.length() < 3 || name.length() > 100) {
            return false;
        }
        
        String regex = "^[a-zA-Z0-9\\s\\-_]+$";
        return name.matches(regex);
    }
    
    public static String sanitizePortfolioName(String name) {
        if (name == null) {
            return "";
        }
        return name.trim();
    }
}