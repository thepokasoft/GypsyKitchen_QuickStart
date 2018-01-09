package in.gk.app.model;

import javax.persistence.ColumnResult;
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
@Table(name="orderlineitem")
@SqlResultSetMappings(
		{
			@SqlResultSetMapping(
					name = "ItemCount",
					entities = @EntityResult(
									entityClass = Product.class,
									fields = {
											@FieldResult(name = "id", column = "id"),
											@FieldResult(name = "name", column = "name"),
											@FieldResult(name = "price", column = "price"),
									}),
									columns = @ColumnResult(name = "quantity" ,type=Long.class)
							)
		})

public class OrderLineItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	Integer orderid;
	Integer productid;
	Integer quantity;
	Double price;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderLineItem [orderLineItemID=" + id + ", orderID=" + orderid + ", productID=" + productid
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	public OrderLineItem(Integer productID, Integer quantity) {
		super();
		this.productid = productID;
		this.quantity = quantity;
	}
	public OrderLineItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
