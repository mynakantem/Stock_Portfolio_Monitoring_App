package com.stockmonitoringapp.dto;

public class GainLossDTO {
    
    private Long id;
    private Long holdingId;
    private Long stockPriceId;
    private Double lossGainAbsolute;
    private Double lossGainPercent;
    
    public GainLossDTO() {
    }
    
    public GainLossDTO(Long id, Long holdingId, Long stockPriceId, Double lossGainAbsolute, Double lossGainPercent) {
        this.id = id;
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