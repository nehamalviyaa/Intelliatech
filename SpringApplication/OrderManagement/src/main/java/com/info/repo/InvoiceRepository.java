package com.info.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.Invoice;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice , Long>{
	 Optional<Invoice> findByOrderId(Long orderId);
}
