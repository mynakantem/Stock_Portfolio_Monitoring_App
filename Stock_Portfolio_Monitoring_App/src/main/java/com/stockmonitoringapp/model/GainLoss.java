package com.stockmonitoringapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gain_loss")
public class GainLoss {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "holding_id")
    private Long holdingId;
    
    @Column(name = "stock_price_id")
    private Long stockPriceId;
    
    @Column(name = "loss_gain_absolute")
    private Double lossGainAbsolute;
    
    @Column(name = "loss_gain_percent")
    private Double lossGainPercent;
    
    public GainLoss() {
    }
    
    public GainLoss(Long holdingId, Long stockPriceId, Double lossGainAbsolute, Double lossGainPercent) {
        this.holdingId = holdingId;
        this.stockPriceId = stockPriceId;
        this.lossGainAbsolute = lossGainAbsolute;
        this.lossGainPercent = lossGainPercent;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getHoldingId() {
        return holdingId;
    }
    
    public void setHoldingId(Long holdingId) {
        this.holdingId = holdingId;
    }
    
    public Long getStockPriceId() {
        return stockPriceId;
    }
    
    public void setStockPriceId(Long stockPriceId) {
        this.stockPriceId = stockPriceId;
    }
    
    public Double getLossGainAbsolute() {
        return lossGainAbsolute;
    }
    
    public void setLossGainAbsolute(Double lossGainAbsolute) {
        this.lossGainAbsolute = lossGainAbsolute;
    }
    
    public Double getLossGainPercent() {
        return lossGainPercent;
    }
    
    public void setLossGainPercent(Double lossGainPercent) {
        this.lossGainPercent = lossGainPercent;
    }
}