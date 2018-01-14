package in.gk.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import in.gk.app.dao.ProductRepository;
import in.gk.app.model.Product;

@Controller
@RequestMapping("product")
public class ProductController extends WebMvcConfigurerAdapter {

	@Autowired
	private ProductRepository productRepo;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllProducts(Map<String, Object> model) {
		model.put("allProducts", this.productRepo.findAll());
		model.put("product", new Product());
		return "ProductsTable";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editProductType(@PathVariable("id") int id, Model model){
        model.addAttribute("product", this.productRepo.findOne(id));
        model.addAttribute("allProducts", this.productRepo.findAll());
        return "ProductsTable";
}

	@RequestMapping( method = RequestMethod.POST)
	public String addProductType(@Valid Product p, BindingResult bindingResult) {
		if ((bindingResult.hasErrors())) {
			return "ProductsTable";
		} else {
			this.productRepo.save(p);
			return "redirect:product";
		}
	}

}
