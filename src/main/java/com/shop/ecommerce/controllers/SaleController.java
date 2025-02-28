package com.shop.ecommerce.controllers;

import com.shop.ecommerce.dtos.SaleDTO;
import com.shop.ecommerce.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Create Sale
    @PostMapping("/create")
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO createdSale = saleService.createSale(saleDTO);
        return ResponseEntity.ok(createdSale);
    }

    // Get Sale by ID
    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable long id) {
        SaleDTO sale = saleService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }

    // Get all Sales
    @GetMapping("/all")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    // Update Sale
    @PutMapping("/update/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable long id, @RequestBody SaleDTO saleDTO) {
        SaleDTO updatedSale = saleService.updateSale(id, saleDTO);
        return ResponseEntity.ok(updatedSale);
    }

    // Delete Sale
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
