package in.gk.app.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.CustomerRepository;
import in.gk.app.dao.ProductRepository;
import in.gk.app.model.Customer;
import in.gk.app.model.Order;
import in.gk.app.model.OrderItem;
import in.gk.app.model.Product;
import in.gk.app.service.OrderItemService;
import in.gk.app.service.OrderService;

@RestController
public class Initialize {

	@Autowired
	private ProductRepository pr;
	@Autowired
	private OrderItemService itemService;
	@Autowired
	private OrderService OrderService;
	@Autowired
	private CustomerRepository cusRepo;
		
	@GetMapping("/ini")
	public String initial()
	{
		pr.save(new Product("Burger",10.0));
		pr.save(new Product("Sandwitch",20.0));
		pr.save(new Product("Rolls",30.0));
		
		// Order 1 
		Customer a = new Customer();
		a.setName("Dhyanesh");
		Map<Integer, Integer> itemsMap = new HashMap<>();
		itemsMap.put(1,2);
		OrderService.saveNewOrder(itemsMap,a);
		
		
		return "Success";
	}
	
	@GetMapping("/yes")
	public String test()
	{
	
		System.out.println("**********************YES METHOD");
		Customer dhyan = new Customer();
		dhyan.setName("Dhyanesh");
		System.out.println(dhyan);
		dhyan = cusRepo.save(dhyan);
		System.out.println(dhyan);
		dhyan = cusRepo.save(dhyan);
		System.out.println(dhyan);
		
		itemService.getNotReadyOrderItems();
		return "VeryGood";
	}
	
	@GetMapping("/test")
	public void CompareItems()
	{
		Product Burger = new Product("Burger",10.0);
		Product Roll = new Product("Roll",20.0);
		Customer d = new Customer(); d.setName("Dhyanesh"); d.setId(1);
		Order order1 = new Order();
		OrderItem firstItem = new OrderItem(); 

		firstItem.setId(1);firstItem.setOrder(order1);firstItem.setQuantity(2);
		firstItem.setPrice(20.00);firstItem.setProduct(Burger);
		List<OrderItem> items = new ArrayList<>(); items.add(firstItem);
		order1.setCustomer(d);order1.setItems(items);order1.setId(1);
		
		System.out.println(order1);
		System.out.println(items);
		

		OrderItem firstItemUpdated = new OrderItem(); 
		firstItemUpdated.setOrder(order1);firstItemUpdated.setQuantity(1);
		firstItemUpdated.setPrice(10.00);firstItemUpdated.setProduct(Burger);
		OrderItem secondItem = new OrderItem();
		secondItem.setOrder(order1);secondItem.setQuantity(1);
		secondItem.setPrice(20.00);secondItem.setProduct(Roll);
		
		List<OrderItem> updateditems = new ArrayList<>(); items.add(firstItem);
		updateditems.add(firstItem);
		updateditems.add(secondItem);
		
		
		
		
		
	}
	
}