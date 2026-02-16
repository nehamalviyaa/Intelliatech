package com.info.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems , Integer>{

}
