package in.gk.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	@NotNull
	@Size(min=1)
	String name;
	@NotNull
	Double price;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [productID=" + id + ", productName=" + name + ", price=" + price + "]";
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
}
