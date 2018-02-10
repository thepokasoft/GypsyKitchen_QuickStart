package in.gk.app.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.OrderRepository;
import in.gk.app.model.Order;

@RestController
@RequestMapping(value="/api/customerorder")
public class CustomerOrderRestController {

	@Autowired
	private OrderRepository customerOrderRepo;
	
	@GetMapping("")
	public List<Order> getCustomerOrder()
	{
		System.out.println(customerOrderRepo.findAll());
		return customerOrderRepo.findAll();
	}
	
	
}
