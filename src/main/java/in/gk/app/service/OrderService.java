package in.gk.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gk.app.dao.CustomerRepository;
import in.gk.app.dao.OrderItemRepository;
import in.gk.app.dao.OrderRepository;
import in.gk.app.model.Customer;
import in.gk.app.model.Order;
import in.gk.app.model.OrderItem;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderItemRepository orderLineItemRepo;
	@Autowired
	private CustomerRepository customerRepo;

	public Iterable<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	public Iterable<Order> getOpenOrders() {

		return orderRepo.findByStatus(false);
	}

	public Boolean deleteOrder(Integer id) {
		orderRepo.delete(id);
		return true;
	}

	public Order getSingleOrder(Integer id) {
		return orderRepo.findOne(id);
	}

	public Order saveNewOrder(Map<Integer, Integer> itemsMap, Customer customer) {

		customer = customerService.saveCustomer(customer);

		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderstarttime(new Date());
		order.setPaid(false);
		order.setStatus(false);
		order = orderRepo.save(order);
		List<OrderItem> items = orderItemService.saveOrderItem(itemsMap, order.getId());
		double price = 0.00;
		for (OrderItem item : items)
			price = price + item.getPrice();
		order.setItems(items);
		order.setPrice(price);
		return orderRepo.save(order);
	}

	public Order saveOrder(Order order, List<OrderItem> orderLineItems, Customer customer) {

		Customer cust = customerRepo.save(customer);
		order.setCustomer(cust);
		Order co = orderRepo.save(order);
		for (OrderItem orderLineItem : orderLineItems) {
			orderLineItem.setOrder(co);
			orderLineItem.setPrice(orderLineItem.getProduct().getPrice());
		}
		Iterable<OrderItem> olt = orderLineItemRepo.save(orderLineItems);
		List<OrderItem> items = new ArrayList<>();
		Double total = 0.0;
		for (OrderItem LineItem : olt) {
			total = total + LineItem.getPrice() * LineItem.getQuantity();
			items.add(LineItem);
		}
		co.setPrice(total);
		co.setItems(items);
		orderRepo.save(co);
		return co;
	}

	public Order updateOrder(Map<Integer, Integer> itemsMap, Customer customer, Integer orderId) {
		Order order = orderRepo.getOne(orderId);
		customer = customerRepo.save(customer);
		order.getItems().clear();
		// Clear items From DB
		orderItemService.deleteItemsForOrder(orderId);
		List<OrderItem> olt = orderItemService.saveOrderItem(itemsMap, orderId);
		List<OrderItem> items = new ArrayList<>();
		Double total = 0.0;
		for (OrderItem LineItem : olt) {
			total = total + LineItem.getPrice() * LineItem.getQuantity();
			items.add(LineItem);
		}
		order.setPrice(total);
		order.setItems(items);
		orderRepo.save(order);
		return order;
	}
	
	public Order updateOrderStatus(Integer orderId) {
		Order order = orderRepo.getOne(orderId);
		List<OrderItem> items = orderLineItemRepo.findByOrderId(orderId);
		if(order.getPaid() == false)
		{
			return order;
		}
		for(OrderItem item : items) {
			if(item.getItemCurrentStatus() != "Completed") {
				return order;
			}
		}
		order.setStatus(true);
		order.setOrderfinishtime(new Date());
		order = orderRepo.save(order);
		return order;
	}

	public Boolean updateOrderPaid(Integer orderId) {
		Order order = orderRepo.getOne(orderId);
		order.setPaid(true);
		orderRepo.save(order);
		return true;
	}

}
/*
 * https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
 */