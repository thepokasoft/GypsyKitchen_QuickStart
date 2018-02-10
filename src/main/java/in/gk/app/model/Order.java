package in.gk.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="customerorder")		
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	@OneToOne
	Customer customer;
	Double price;
	Boolean paid;
	Date orderstarttime;
	Date orderfinishtime;
	Boolean status;
	@JsonIgnore
	@OneToMany(mappedBy="order")
	List<OrderItem> items;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public Date getOrderstarttime() {
		return orderstarttime;
	}
	public void setOrderstarttime(Date orderstarttime) {
		this.orderstarttime = orderstarttime;
	}
	public Date getOrderfinishtime() {
		return orderfinishtime;
	}
	public void setOrderfinishtime(Date orderfinishtime) {
		this.orderfinishtime = orderfinishtime;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", customer=" + customer + ", price=" + price + ", paid=" + paid
				+ ", orderstarttime=" + orderstarttime + ", orderfinishtime=" + orderfinishtime + ", status=" + status
				+ ", items=" + items + "]";
	}
	
	
}