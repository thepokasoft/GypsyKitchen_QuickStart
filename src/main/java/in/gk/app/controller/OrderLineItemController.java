package in.gk.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.gk.app.model.Customer;
import in.gk.app.model.CustomerOrder;
import in.gk.app.model.OrderLineItem;
import in.gk.app.model.Product;
import in.gk.app.service.CustomerOrderService;

@Controller
@RequestMapping("lineitem")
public class OrderLineItemController {

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLineItems(Map<String, Object> model) {
		
		List<Object[]> objects = customerOrderService.getLineItemsAndAggrigatedQuantity();
		List<Product> products = new ArrayList<>();
		
		Map<String , Long> OrderLineMAp= new HashMap<>();
		
		for(int i=0; i<objects.size(); i++) {
			OrderLineMAp.put(((Product)objects.get(i)[0]).getName(),
					((Long)objects.get(i)[1]));
			products.add((Product)objects.get(i)[0]);
		}
		Set<Product> uniqueProduct = new LinkedHashSet<>(products);
		model.put("uniqueProduct", uniqueProduct);
		model.put("OrderLineMAp", OrderLineMAp);
		return "LineItem";
	}
	
}
