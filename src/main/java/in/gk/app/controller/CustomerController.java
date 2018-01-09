package in.gk.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.gk.app.dao.CustomerRepository;
import in.gk.app.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllCustomers(Map<String, Object> model) {
		model.put("allCustomer", this.customerRepo.findAll());
		model.put("customer", new Customer());
		return "CustomerTable";
	}
	
	@RequestMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model){
        model.addAttribute("customer", this.customerRepo.findOne(id));
        model.addAttribute("allCustomer", this.customerRepo.findAll());
        return "CustomerTable";
    }
	
	@RequestMapping(value= "/addCustomer", method = RequestMethod.POST)
	public String addProductType(@ModelAttribute("customer") Customer customer){
		this.customerRepo.save(customer);
		return "redirect:";
	}
	
}