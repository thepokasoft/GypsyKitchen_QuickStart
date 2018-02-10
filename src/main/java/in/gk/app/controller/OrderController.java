package in.gk.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.gk.app.dao.OrderRepository;
import in.gk.app.dao.ProductRepository;
import in.gk.app.model.Customer;
import in.gk.app.model.Order;
import in.gk.app.model.OrderItem;
import in.gk.app.model.OrderItemList;
import in.gk.app.service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository customerOrderRepo;

	@Autowired
	private ProductRepository productRepo;

	@RequestMapping(value = { "", "saveorder" }, method = RequestMethod.GET)
	public String getCustomerOrders(Map<String, Object> model) {
		model.put("allOrders", orderService.getOpenOrders());
		return "OrdersDisplay";
	}

	@RequestMapping(value = { "/1" }, method = RequestMethod.GET)
	public String getCustomerOrders1(Map<String, Object> model) {
		model.put("allOrders", orderService.getAllOrders());
		return "CustomerOrderDisplay2";
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newCustomerOrder(Map<String, Object> model) {
		model.put("customer", new Customer());
		model.put("order", new Order());
		model.put("allProducts", productRepo.findAll());
		OrderItemList oliLists = new OrderItemList();
		oliLists.getOlis().add(new OrderItem());
		model.put("olim", oliLists);
		return "OrderNew";
	}

	@RequestMapping(value = "saveorder", params = { "addItem" }, method = RequestMethod.POST)
	public String addProductCustomerOrder(@ModelAttribute("olim") OrderItemList olist, Map<String, Object> model,
			@ModelAttribute("customer") Customer customer, Order order) {
		model.put("customer", customer);
		model.put("allProducts", productRepo.findAll());
		olist.getOlis().add(new OrderItem());
		model.put("olim", olist);
		model.put("order", order);
		return "OrderNew";
	}

	@RequestMapping(value = "saveorder", params = { "removeItem" }, method = RequestMethod.POST)
	public String removeProductCustomerOrder(@ModelAttribute("olim") OrderItemList olist, Map<String, Object> model,
			@ModelAttribute("customer") Customer customer, HttpServletRequest req, Order order) {
		model.put("customer", customer);
		model.put("allProducts", productRepo.findAll());
		Integer rowId = Integer.valueOf(req.getParameter("removeItem"));
		olist.getOlis().remove(rowId.intValue());
		model.put("olim", olist);
		model.put("order", order);
		return "OrderNew";
	}

	@RequestMapping(value = "saveorder", params = { "save" }, method = RequestMethod.POST)
	public String saveCustomerOrder(@Valid @ModelAttribute("olim") OrderItemList olist, @Valid Customer customer,
			@Valid Order order, Map<String, Object> model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "OrderNew";
		} else {
			Map<Integer, Integer> itemsMap = new HashMap<>();
			for (OrderItem item : olist.getOlis())
				itemsMap.put(item.getProduct().getId(), item.getQuantity());
			if (null == order.getId()) {
				orderService.saveNewOrder(itemsMap, customer);
			} else {
				orderService.updateOrder(itemsMap, customer, order.getId());
			}

			return "redirect:/order";
		}
	}

	@RequestMapping(value = "finishOrder-{id}", method = RequestMethod.GET)
	public String finishCustomerOrder(@PathVariable("id") int id, Map<String, Object> model) {
		Order co = customerOrderRepo.findOne(id);
		co.setStatus(true);
		co.setOrderfinishtime(new Date());
		customerOrderRepo.save(co);
		model.put("allOrders", orderService.getAllOrders());
		return "redirect:/order";
	}

	@RequestMapping(value = "paidOrder-{id}", method = RequestMethod.GET)
	public String paidCustomerOrder(@PathVariable("id") int id, Map<String, Object> model) {
		Order co = customerOrderRepo.findOne(id);
		co.setPaid(true);
		customerOrderRepo.save(co);
		model.put("allOrders", orderService.getAllOrders());
		return "redirect:/order";
	}

	@RequestMapping(value = "editorder-{id}", method = RequestMethod.GET)
	public String editOrder(@PathVariable("id") int id, Map<String, Object> model) {

		Order orderEdit = orderService.getSingleOrder(id);
		model.put("customer", orderEdit.getCustomer());
		model.put("allProducts", productRepo.findAll());
		OrderItemList oliLists = new OrderItemList();
		oliLists.getOlis().addAll(orderEdit.getItems());
		model.put("olim", oliLists);
		model.put("order", orderEdit);
		return "OrderNew";
	}
}