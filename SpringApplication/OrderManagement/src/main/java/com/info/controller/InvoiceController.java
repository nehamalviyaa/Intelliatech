package com.info.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.InvoiceResponseDTO;
import com.info.entity.Invoice;
import com.info.entity.InvoiceItem;
import com.info.repo.InvoiceItemRepository;
import com.info.service.InvoiceMapper;
import com.info.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	
	
	private final InvoiceService invoiceService;
    private final InvoiceItemRepository itemRepo;
    private final InvoiceMapper mapper;
    
    public InvoiceController(InvoiceService invoiceService , InvoiceItemRepository itemRepo, InvoiceMapper mapper) {
    	this.invoiceService = invoiceService;
    	this.itemRepo = itemRepo;
    	this.mapper = mapper;
    }
    

    @GetMapping("/order/{orderId}")
    public InvoiceResponseDTO getInvoice(@PathVariable Long orderId) {

        Invoice invoice = invoiceService.getByOrderId(orderId);
        List<InvoiceItem> items = itemRepo.findByInvoice(invoice);

        return mapper.toDTO(invoice, items);
    }
	

}
