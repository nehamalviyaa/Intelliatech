package com.info.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.info.InvoiceNumberGenerator;
import com.info.entity.Invoice;
import com.info.entity.InvoiceItem;
import com.info.entity.Order;
import com.info.entity.OrderItems;
import com.info.entity.PaymentStatus;
import com.info.exception.ResourceNotFoundException;
import com.info.repo.InvoiceItemRepository;
import com.info.repo.InvoiceRepository;

@Service
public class InvoiceService {

	private final InvoiceRepository invoiceRepo;
	private final InvoiceItemRepository invoiceItemRepo;
	private final InvoiceNumberGenerator generator;
	private final DiscountService service;
	
	public InvoiceService(
			InvoiceRepository invoiceRepo , 
			InvoiceItemRepository invoiceItemRepo ,
			InvoiceNumberGenerator generator ,
			DiscountService service) {
		
		this.invoiceRepo = invoiceRepo;
		this.invoiceItemRepo = invoiceItemRepo;
		this.generator = generator;
		this.service = service;
	}
	
	
	//INVOICE 
	public Invoice generateInvoice(Order order , List<OrderItems> orderItems) {
		
		Invoice invoice = new Invoice();
		invoice.setOrder(order);
		invoice.setInvoiceNumber(generator.generate(order.getId()));
		invoice.setInvoiceDate(LocalDateTime.now());
		invoice.setDueDate(LocalDateTime.now().plusDays(7));
		invoice.setPaymentStatus(PaymentStatus.PENDING);
		
		invoice = invoiceRepo.save(invoice);
		double subtotal = 0;
		
		for(OrderItems oi : orderItems) {
		double total = oi.getPrice()* oi.getQuantity();
		subtotal += total;
		
		
		InvoiceItem item = new InvoiceItem();
		item.setInvoice(invoice);
		item.setProduct(oi.getProduct());
		item.setQuantity(oi.getQuantity());
		item.setUnitPrice(oi.getPrice());
		item.setTotalPrice(total);
		
		
		invoiceItemRepo.save(item);
		
		}
		
		double discount = service.calculateDiscount(subtotal);
        double tax = subtotal * 0.18;

        invoice.setSubtotal(subtotal);
        invoice.setDiscountAmount(discount);
        invoice.setTaxAmount(tax);
        invoice.setFinalAmount(subtotal + tax - discount);

        return invoiceRepo.save(invoice);
		
	}
	
	
	//FIND INVOICE BY ORDERID
	public Invoice getByOrderId(Long orderId) {
        return invoiceRepo.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
    }
	
	
	

}
