package com.firstproject.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstproject.demo.entities.OrderProduct;
import com.firstproject.demo.pk.OrderProductPK;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {

}
