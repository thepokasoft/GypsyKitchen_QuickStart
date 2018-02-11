package in.gk.app.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.OrderItemRepository;
import in.gk.app.model.OrderItem;

@RestController
@RequestMapping(value="/api/item")
public class OrderItemRestController {

	@Autowired
	private OrderItemRepository orderLineItemRepo;
	
	@GetMapping("")
	public List<OrderItem> getOrderLineItem()
	{
		return orderLineItemRepo.findAll();
	}
	
	
}
