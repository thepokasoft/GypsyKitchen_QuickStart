package in.gk.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gk.app.dao.CustomerRepository;
import in.gk.app.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	public Iterable<Customer> getAllCustomers(){
		return customerRepo.findAll();
	}
	
	public Customer getSingleCustomer(Integer id)
	{
		return customerRepo.findOne(id);
	}
	
	public Boolean deleteCustomer(Integer id) {
		customerRepo.delete(id);
		return true;
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	public List<Customer> saveCustomers(List<Customer> customers) {
		return customerRepo.save(customers);
	}
}
