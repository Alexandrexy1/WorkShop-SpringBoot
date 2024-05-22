package com.firstproject.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.firstproject.demo.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant date;
	
	// if i use JsonIgnore here, it returns the User along with the orders
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	private Integer orderStatus;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderProduct> products = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {}

	public Order(Long id, Instant date, OrderStatus orderStatus, User client) {
		this.id = id;
		this.date = date;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}
	
	public void setDate(Instant date) {
		this.date = date;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus.getCode();
	}
	
	public User getClient() {
		return client;
	}
	
	public Set<OrderProduct> getProducts() {
		return products;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	 
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}