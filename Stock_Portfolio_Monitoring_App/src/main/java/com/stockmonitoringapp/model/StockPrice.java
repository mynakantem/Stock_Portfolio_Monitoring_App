package com.stockmonitoringapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_prices")
@Data
@Builder

public class StockPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 10)
	private String symbol;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(name = "last_updated", nullable = false)
	private LocalDateTime lastUpdated;

}
