package in.gk.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.gk.app.service.OrderItemService;

@Controller
@RequestMapping("items")
public class OrderItemController {

	@Autowired
	OrderItemService orderItemservice;
	
	@GetMapping("")
	public String currentItemsToProcess(Map<String, Object> model)
	{
		Map<String,String> itemToProcessMap = orderItemservice.getNotReadyOrderItems();
		model.put("items", itemToProcessMap);
		model.put("uniqueitems",itemToProcessMap.keySet());
		System.out.println(itemToProcessMap);
		return "LineItem";
	}
	

}
