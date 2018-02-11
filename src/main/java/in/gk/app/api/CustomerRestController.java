package in.gk.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.CustomerRepository;
import in.gk.app.model.Customer;

@RestController
@RequestMapping(value="/api/customer")
public class CustomerRestController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	@RequestMapping(value = "/{customerID}", method = RequestMethod.GET)
	public Customer retrieveCustomer(@PathVariable String customerID) {
		return customerRepo.findOne(Integer.parseInt(customerID));
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerRepo.save(customer);
	}
	
	
}
