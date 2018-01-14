package in.gk.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.gk.app.dao.CustomerOrderRepository;
import in.gk.app.dao.ProductRepository;
import in.gk.app.model.Customer;
import in.gk.app.model.CustomerOrder;
import in.gk.app.model.OrderLineItem;
import in.gk.app.model.OrderLineItemList;
import in.gk.app.model.Product;
import in.gk.app.service.CustomerOrderService;
import in.gk.app.service.OrderLineItemService;

@Controller
@RequestMapping("customerorder")
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private OrderLineItemService orderLineItemService;

	@Autowired
	private CustomerOrderRepository customerOrderRepo;

	@Autowired
	private ProductRepository productRepo;

	@RequestMapping(value = { "/", "saveorder" }, method = RequestMethod.GET)
	public String getCustomerOrders(Map<String, Object> model) {
		model.put("allOrders", customerOrderService.getAllOrder());
		return "CustomerOrderDisplay";
	}

	@RequestMapping(value = { "/1" }, method = RequestMethod.GET)
	public String getCustomerOrders1(Map<String, Object> model) {
		List<Object[]> orderCustomerResults = customerOrderService.getOrderCustomer();
		List<Object[]> orderItemResults = orderLineItemService.getOrderLineItemforActiveOrders();

		Map<String, String> orderDetailsMap;
		List<String> Items = new ArrayList<>();
		Map<String, List<String>> orderItemsMap = new HashMap<>();
		Set<Product> uniqueProduct = new LinkedHashSet<>();
		List<Map<String, String>> orderList = new ArrayList<>();

		Map<String, String> orderItemDetailsMap = new HashMap<>();

		for (int i = 0; i < orderCustomerResults.size(); i++) {
			orderDetailsMap = new HashMap<>();
			orderDetailsMap.put("cname", ((Customer) orderCustomerResults.get(i)[0]).getName());
			orderDetailsMap.put("oid", ((CustomerOrder) orderCustomerResults.get(i)[1]).getId().toString());
			orderDetailsMap.put("ostatus", ((CustomerOrder) orderCustomerResults.get(i)[1]).getStatus().toString());
			orderDetailsMap.put("opaid", ((CustomerOrder) orderCustomerResults.get(i)[1]).getPaid().toString());
			orderDetailsMap.put("oprice", ((CustomerOrder) orderCustomerResults.get(i)[1]).getPrice().toString());

			for (int j = 0; j < orderItemResults.size(); j++) {
				if (((OrderLineItem) orderItemResults.get(j)[1])
						.getOrderid() == ((CustomerOrder) orderCustomerResults.get(i)[1]).getId()) {
					orderDetailsMap.put(((Product) orderItemResults.get(j)[2]).getName(),
							((OrderLineItem) orderItemResults.get(j)[1]).getQuantity().toString());
				}
				uniqueProduct.add((Product) orderItemResults.get(j)[2]);
			}

			orderList.add(orderDetailsMap);

		}

		model.put("uniqueProduct", uniqueProduct);

		// model.put("orderDetailsMap", orderDetailsMap);
		model.put("orderList", orderList);

		return "CustomerOrderDisplay2";
	}

	@RequestMapping(value = "takeorder", method = RequestMethod.GET)
	public String newCustomerOrder(Map<String, Object> model) {
		model.put("customer", new Customer());
		model.put("allProducts", productRepo.findAll());
		OrderLineItemList oliLists = new OrderLineItemList();
		oliLists.getOlis().add(new OrderLineItem());
		model.put("olim", oliLists);
		return "CustomerOrderNew";
	}

	@RequestMapping(value = "saveorder", params = { "save" }, method = RequestMethod.POST)
	public String saveCustomerOrder(@Valid @ModelAttribute("olim") OrderLineItemList olist, @Valid Customer customer,
			Map<String, Object> model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "CustomerOrderNew";
		} else {
			CustomerOrder customerOrder = new CustomerOrder();
			customerOrder.setOrdertime(new Date());
			customerOrder.setPaid(false);
			customerOrder.setStatus(false);
			model.put("allOrders", customerOrderService.saveOrder(customerOrder, olist.getOlis(), customer));
			customerOrderService.getLineItemsAndAggrigatedQuantity();
			return "CustomerOrderDisplay";
		}
	}

	@RequestMapping(value = "saveorder", params = { "addRow" }, method = RequestMethod.POST)
	public String addProductCustomerOrder(@ModelAttribute("olim") OrderLineItemList olist, Map<String, Object> model,
			@ModelAttribute("customer") Customer customer) {
		model.put("customer", customer);
		model.put("allProducts", productRepo.findAll());
		olist.getOlis().add(new OrderLineItem());
		model.put("olim", olist);
		return "CustomerOrderNew";
	}

	@RequestMapping(value = "saveorder", params = { "removeRow" }, method = RequestMethod.POST)
	public String removeProductCustomerOrder(@ModelAttribute("olim") OrderLineItemList olist, Map<String, Object> model,
			@ModelAttribute("customer") Customer customer, HttpServletRequest req) {
		model.put("customer", customer);
		model.put("allProducts", productRepo.findAll());

		Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		olist.getOlis().remove(rowId.intValue());
		model.put("olim", olist);
		return "CustomerOrderNew";
	}

	@RequestMapping(value = "finishOrder-{id}", method = RequestMethod.GET)
	public String finishCustomerOrder(@PathVariable("id") int id, Map<String, Object> model) {
		CustomerOrder co = customerOrderRepo.findOne(id);
		co.setStatus(true);
		co.setOrderfinishtime(new Date());
		customerOrderRepo.save(co);
		model.put("allOrders", customerOrderService.getAllOrder());
		return "redirect:";
	}

	@RequestMapping(value = "paidOrder-{id}", method = RequestMethod.GET)
	public String paidCustomerOrder(@PathVariable("id") int id, Map<String, Object> model) {
		CustomerOrder co = customerOrderRepo.findOne(id);
		co.setPaid(true);
		customerOrderRepo.save(co);
		model.put("allOrders", customerOrderService.getAllOrder());
		return "redirect:";
	}
	
	@RequestMapping(value = "editorder-{id}", method = RequestMethod.GET)
	public String editOrder(Map<String, Object> model)
	{

		return "CustomerOrderNew";
	}
}