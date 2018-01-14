package in.gk.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;


@Entity
@Table(name="customerorder")
@SqlResultSetMappings(
		{
			@SqlResultSetMapping(
					name = "orderCustomerMapping",
					entities = {
							@EntityResult(
									entityClass = Customer.class,
									fields = {
											@FieldResult(name = "id", column = "cid"),
											@FieldResult(name = "name", column = "cname"),
											@FieldResult(name = "email", column = "cemail"),
											@FieldResult(name = "phone", column = "cphone"),
									}),
							@EntityResult(
									entityClass = CustomerOrder.class,
									fields = {
											@FieldResult(name = "id", column = "coid"),
											@FieldResult(name = "customerid", column = "cocustomerid"),
											@FieldResult(name = "price", column = "coprice"),
											@FieldResult(name = "paid", column = "copaid"),
											@FieldResult(name = "ordertime", column = "coordertime"),
											@FieldResult(name = "orderfinishtime", column = "coorderfinishtime"),
											@FieldResult(name = "status", column = "costatus"),
									}),
							
							}
					)
		})
				
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	Integer customerid;
	Double price;
	Boolean paid;
	Date ordertime;
	Date orderfinishtime;
	Boolean status;
	
	public Date getOrderfinishtime() {
		return orderfinishtime;
	}
	public void setOrderfinishtime(Date orderfinishtime) {
		this.orderfinishtime = orderfinishtime;
	}
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CustomerOrder [customerOrderID=" + id + ", customerID=" + customerid + ", price=" + price
				+ ", paid=" + paid + ", orderTime=" + ordertime + ", status=" + status + "]";
	}
	
	
}
