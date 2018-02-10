package in.gk.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import in.gk.app.model.Product;
import in.gk.app.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController extends WebMvcConfigurerAdapter {

	@Autowired
	private ProductService productService;

	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllProducts(Map<String, Object> model) {
		model.put("allProducts", this.productService.getAllProducts());
		model.put("product", new Product());
		return "ProductsTable";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editProductType(@PathVariable("id") int id, Model model){
        model.addAttribute("product", this.productService.getSingleProduct(id));
        model.addAttribute("allProducts", this.productService.getAllProducts());
        return "ProductsTable";
}

	@RequestMapping( method = RequestMethod.POST)
	public String addProduct(@Valid Product p, BindingResult bindingResult, Model model) {
		if ((bindingResult.hasErrors())) {
			model.addAttribute("allProducts", this.productService.getAllProducts());
			return "ProductsTable";
		} else {
			this.productService.saveProduct(p);
			return "redirect:product";
		}
	}

}
