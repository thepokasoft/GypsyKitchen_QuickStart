package in.gk.app.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.OrderRepository;
import in.gk.app.model.Order;

@RestController
@RequestMapping(value="/api/order")
public class OrderRestController {

	@Autowired
	private OrderRepository customerOrderRepo;
	
	@GetMapping("")
	public List<Order> getCustomerOrder()
	{
		return customerOrderRepo.findAll();
	}
	
	
}
