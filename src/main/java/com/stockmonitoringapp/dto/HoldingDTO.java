package com.stockmonitoringapp.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class HoldingDTO {

    private Long id;

    @NotBlank(message = "Stock symbol cannot be empty")
    @Pattern(regexp = "^[A-Z]{1,10}$", message = "Stock symbol must be uppercase only")
    private String symbol;

    @NotNull(message = "Quantity cannot be zero")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Buy price cannot be zero")
    @DecimalMin(value = "0.01", message = "Buy price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Buy price format wrong")
    private BigDecimal buyPrice;


    public HoldingDTO() {
    }

    public HoldingDTO(Long id, String symbol, Integer quantity, BigDecimal buyPrice) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }
}
