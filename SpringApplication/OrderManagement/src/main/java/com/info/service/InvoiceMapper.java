package com.info.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.info.dto.InvoiceItemDTO;
import com.info.dto.InvoiceResponseDTO;
import com.info.entity.Invoice;
import com.info.entity.InvoiceItem;

@Component
public class InvoiceMapper {

    public InvoiceResponseDTO toDTO(Invoice invoice,
                                    List<InvoiceItem> items) {

        InvoiceResponseDTO dto = new InvoiceResponseDTO();

        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setOrderId(invoice.getOrder().getId().intValue());
        dto.setOrderReference(invoice.getOrder().getReference());

        dto.setUserId(invoice.getOrder().getUser().getId().intValue());
        dto.setCustomerName(invoice.getOrder().getUser().getName());
        dto.setCustomerEmail(invoice.getOrder().getUser().getEmail());

        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setDueDate(invoice.getDueDate());

        dto.setItems(
            items.stream().map(i -> {
                InvoiceItemDTO itemDTO = new InvoiceItemDTO();
                itemDTO.setProductId(i.getProduct().getId().intValue());
                itemDTO.setProductName(i.getProduct().getName());
                itemDTO.setQuantity(i.getQuantity());
                itemDTO.setUnitPrice(i.getUnitPrice());
                itemDTO.setTotalPrice(i.getTotalPrice());
                return itemDTO;
            }).toList()
        );

        dto.setSubtotal(invoice.getSubtotal());
        dto.setDiscountAmount(invoice.getDiscountAmount());
        dto.setTaxAmount(invoice.getTaxAmount());
        dto.setFinalAmount(invoice.getFinalAmount());
        dto.setPaymentStatus(invoice.getPaymentStatus().name());

        return dto;
    }
}
