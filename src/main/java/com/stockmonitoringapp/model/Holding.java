package com.stockmonitoringapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;

@Entity
@Table(name = "holdings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Holding {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 10)
    @NotBlank(message = "Stock symbol cannot be empty")
    @Pattern(regexp = "^[A-Z]{1,10}$", 
             message = "Stock symbol must be uppercase only")
    private String symbol;
    
    @Column(nullable = false)
    @NotNull(message = "Quantity cannot be zero")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    @Column(name = "buy_price", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Buy price cannot be zero")
    @DecimalMin(value = "0.01", message = "Buy price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Buy price format wrong")
    private BigDecimal buyPrice;
}