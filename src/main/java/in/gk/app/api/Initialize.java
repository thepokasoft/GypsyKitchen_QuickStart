package in.gk.app.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.ProductRepository;
import in.gk.app.model.Customer;
import in.gk.app.model.CustomerOrder;
import in.gk.app.model.OrderLineItem;
import in.gk.app.model.Product;
import in.gk.app.service.CustomerOrderService;

@RestController
public class Initialize {

	@Autowired
	ProductRepository pr;
	
	@Autowired
	private CustomerOrderService customerOrderService;
		
	@GetMapping("/ini")
	public String initial()
	{
		pr.save(new Product("Burger",10.0));
		pr.save(new Product("Sandwitch",20.0));
		pr.save(new Product("Rolls",30.0));
		
		return "Success";
	}
	@GetMapping("/yes")
	public String getCustomerOrder() {
		
		pr.save(new Product("Burger",10.0));
		pr.save(new Product("Sandwitch",20.0));
		pr.save(new Product("Rolls",30.0));
		
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setOrdertime(new Date());
		customerOrder.setPaid(false);
		customerOrder.setStatus(false);
		
		Customer customer = new Customer();
		customer.setName("Dhyanesh");
		List<OrderLineItem> orderLineItems = new ArrayList<OrderLineItem>();

		OrderLineItem ol1 = new OrderLineItem(1, 2);
		OrderLineItem ol2 = new OrderLineItem(2,2);
		OrderLineItem ol3 = new OrderLineItem(3,1);
		orderLineItems.add(ol1);
		orderLineItems.add(ol2);
		orderLineItems.add(ol3);
		
		customerOrderService.saveOrder(customerOrder, orderLineItems, customer);
		
		customerOrderService.getAll();
		
		return "good";
		
	}
}