package in.gk.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gk.app.dao.OrderLineItemRepository;
import in.gk.app.model.OrderLineItem;

@Service
public class OrderLineItemService {

	@Autowired
	private OrderLineItemRepository orderLineItemRepo;
	public Iterable<OrderLineItem> getAllOrderLineItem(){
		return orderLineItemRepo.findAll();
	}
	
	public Boolean deleteOrderLineItem(Integer id) {
		orderLineItemRepo.delete(id);
		return true;
	}

	public Iterable<OrderLineItem> saveOrderLineItem(Integer customerOrderID, List<OrderLineItem> orderLineItems) {
		
		for (OrderLineItem orderLineItem : orderLineItems) {
			orderLineItem.setOrderid(customerOrderID);
		}
		return orderLineItemRepo.save(orderLineItems);
		
	}
	
}
