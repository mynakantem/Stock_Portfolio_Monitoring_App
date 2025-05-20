package com.stockmonitoringapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.stockmonitoringapp.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/export/excel")
	public ResponseEntity<byte[]>exportExcelReport(@RequestParam Long portfolioId) {
		byte[] data = reportService.generateExcelReport(portfolioId);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setContentDisposition(ContentDisposition.attachment().filename("portfolio_report.xlsx").build());
		
		return new ResponseEntity<>(data, headers, HttpStatus.OK);
	}
	
}
