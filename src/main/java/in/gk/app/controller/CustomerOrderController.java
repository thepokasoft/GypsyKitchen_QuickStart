package in.gk.app.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import in.gk.app.service.CustomerOrderService;

@Controller
@RequestMapping("customerorder")
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private CustomerOrderRepository customerOrderRepo;

	@Autowired
	private ProductRepository productRepo;

	@RequestMapping(value = {"/","saveorder"}, method = RequestMethod.GET)
	public String getCustomerOrders(Map<String, Object> model) {
		model.put("allOrders", customerOrderService.getAllOrder());
		return "CustomerOrderDisplay";
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
	public String saveCustomerOrder(@ModelAttribute("olim") OrderLineItemList olist,
			@ModelAttribute("customer") Customer customer, Map<String, Object> model) {
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setOrdertime(new Date());
		customerOrder.setPaid(false);
		customerOrder.setStatus(false);
		model.put("allOrders",customerOrderService.saveOrder(customerOrder, olist.getOlis(), customer));
		customerOrderService.getAll();
		return "CustomerOrderDisplay";
	}

	@RequestMapping(value = "saveorder", params = { "addRow" }, method = RequestMethod.POST)
	public String addProductCustomerOrder(@ModelAttribute("olim") OrderLineItemList olist, Map<String, Object> model) {
		model.put("customer", new Customer());
		model.put("allProducts", productRepo.findAll());
		olist.getOlis().add(new OrderLineItem());
		model.put("olim", olist);
		return "CustomerOrderNew";
	}

	@RequestMapping(value = "saveorder", params = { "removeRow" }, method = RequestMethod.POST)
	public String removeProductCustomerOrder(@ModelAttribute("olim") OrderLineItemList olist, Map<String, Object> model,
			HttpServletRequest req) {
		model.put("customer", new Customer());
		model.put("allProducts", productRepo.findAll());

		Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		olist.getOlis().remove(rowId.intValue());
		model.put("olim", olist);
		return "CustomerOrderNew";
	}
	
	@RequestMapping(value = "finishOrder-{id}", method = RequestMethod.GET)
    public String finishCustomerOrder(@PathVariable("id") int id, Map<String, Object> model){
		CustomerOrder co = customerOrderRepo.findOne(id);
		co.setStatus(true);	
		customerOrderRepo.save(co);
		model.put("allOrders", customerOrderService.getAllOrder());
        return "redirect:";
    }
	@RequestMapping(value = "paidOrder-{id}", method = RequestMethod.GET)
    public String paidCustomerOrder(@PathVariable("id") int id, Map<String, Object> model){
		CustomerOrder co = customerOrderRepo.findOne(id);
		co.setPaid(true);
		customerOrderRepo.save(co);
		model.put("allOrders", customerOrderService.getAllOrder());
        return "redirect:";
    }
}