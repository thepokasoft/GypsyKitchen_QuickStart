package in.gk.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gk.app.dao.ProductRepository;
import in.gk.app.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public Iterable<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	public Product getSingleProduct(Integer id)
	{
		return productRepo.findOne(id);
	}
	
	public Boolean deleteProduct(Integer id) {
		productRepo.delete(id);
		return true;
	}

	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	
	public List<Product> saveProducts(List<Product> products) {
		return productRepo.save(products);
	}
}
