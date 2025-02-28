package com.shop.ecommerce.services;

import com.shop.ecommerce.dtos.SaleDTO;
import com.shop.ecommerce.dtos.builders.SaleBuilder;
import com.shop.ecommerce.entities.Sale;
import com.shop.ecommerce.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    public SaleDTO createSale(SaleDTO saleDTO) {

        Sale sale = SaleBuilder.toEntity(saleDTO);


        Sale savedSale = saleRepository.save(sale);


        return SaleBuilder.toSaleDTO(savedSale);
    }


    public SaleDTO getSaleById(long id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isPresent()) {
            return SaleBuilder.toSaleDTO(saleOptional.get());
        } else {
            throw new IllegalArgumentException("Sale with ID " + id + " not found");
        }
    }


    public List<SaleDTO> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(SaleBuilder::toSaleDTO)
                .collect(Collectors.toList());
    }


    public SaleDTO updateSale(long id, SaleDTO saleDTO) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isPresent()) {

            Sale sale = saleOptional.get();
            sale.setOldPrice(saleDTO.getOldPrice());
            sale.setNewPrice(saleDTO.getNewPrice());
            sale.setPercent(saleDTO.getPercent());


            Sale updatedSale = saleRepository.save(sale);


            return SaleBuilder.toSaleDTO(updatedSale);
        } else {
            throw new IllegalArgumentException("Sale with ID " + id + " not found");
        }
    }


    public void deleteSale(long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Sale with ID " + id + " not found");
        }
    }
}
