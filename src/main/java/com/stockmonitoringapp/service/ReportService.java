package com.stockmonitoringapp.service;

public interface ReportService {
	byte[] generateExcelReport(Long portfolioId);
}
