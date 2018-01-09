package in.gk.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.ProductRepository;
import in.gk.app.model.Product;

@RestController
@RequestMapping(value="/api/product")
public class ProductRestController {

	@Autowired
	private ProductRepository productRepo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Product> getAllCustomers() {
		return productRepo.findAll();
	}
	
	@RequestMapping(value = "/{productID}", method = RequestMethod.GET)
	public Product retrieveCustomer(@PathVariable String productID) {
		return productRepo.findOne(Integer.parseInt(productID));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Product saveCustomer(@RequestBody Product product) {
		return productRepo.save(product);
	}
	
	
}
