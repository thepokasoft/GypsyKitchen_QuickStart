package in.gk.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import in.gk.app.service.OrderItemService;

@Controller
@RequestMapping("item")
public class OrderItemController {

	@Autowired
	OrderItemService orderItemservice;

	@GetMapping("")
	public String currentItemsToProcess(Map<String, Object> model) {
		Map<String, String> itemToProcessMap = orderItemservice.getNotReadyOrderItems();
		model.put("items", itemToProcessMap);
		model.put("uniqueitems", itemToProcessMap.keySet());
		System.out.println(itemToProcessMap);
		return "LineItem";
	}

	@GetMapping("/finish/{id}")
	public String finishItem(@PathVariable("id") int id) {
		orderItemservice.updateItemStatus(id);
		return "redirect:/order";
	}

}
