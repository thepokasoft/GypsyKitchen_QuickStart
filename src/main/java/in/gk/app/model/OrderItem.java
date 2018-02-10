package in.gk.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderlineitem")
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	@OneToOne
	Product product;
	Integer quantity;
	Double price;
	@ManyToOne
	Order order;
	String itemCurrentStatus;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setItemCurrentStatus(String itemCurrentStatus) {
		this.itemCurrentStatus = itemCurrentStatus;
	}

	
	
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", product=" + product + ", quantity=" + quantity + ", price=" + price
				+ ", order=" + order + ", itemCurrentStatus=" + itemCurrentStatus + "]";
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getItemCurrentStatus() {
		return itemCurrentStatus;
	}
	
	
}
