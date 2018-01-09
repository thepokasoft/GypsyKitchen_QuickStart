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
//"ol.id as olid, ol.orderid as olorderid, ol.price as olprice, ol.productid as olproductid, "
//+ "ol.quantity as olquantity"
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	Integer customerid;
	Double price;
	Boolean paid;
	Date ordertime;
	Boolean status;
	
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
