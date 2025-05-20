package com.stockmonitoringapp.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private HoldingRepository holdingRepository;
	
	@Override
	public byte[] generateExcelReport(Long portfolioId) {
		List<Holding> holdings = holdingRepository.findByPortfolioId(portfolioId);
		
		try (
				Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream()
						) {
			Sheet sheet = workbook.createSheet("Portfolio Report");
			
			//creating the header row
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Symbol");
			header.createCell(1).setCellValue("Quantity");
			header.createCell(2).setCellValue("Buy Price");
			header.createCell(3).setCellValue("Total Value");
			
			//filling in the data rows
			int rowNum = 1;
			for (Holding h : holdings) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(h.getSymbol());
				row.createCell(1).setCellValue(h.getQuantity());
				row.createCell(2).setCellValue(h.getBuyPrice());
				double totalValue = h.getQuantity() * h.getBuyPrice();
				row.createCell(3).setCellValue(h.getTotalValue());
			}
			
			workbook.write(out);
			return out.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException ("Excel Report generation failed" , e);
		}
	}
}
