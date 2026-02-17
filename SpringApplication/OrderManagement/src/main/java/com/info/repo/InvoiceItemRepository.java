package com.info.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.Invoice;
import com.info.entity.InvoiceItem;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem , Long> {

	List<InvoiceItem> findByInvoice(Invoice invoice);
}
