package in.gk.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gk.app.dao.CustomerOrderRepository;
import in.gk.app.dao.CustomerRepository;
import in.gk.app.dao.OrderLineItemRepository;
import in.gk.app.dao.ProductRepository;
import in.gk.app.model.Customer;
import in.gk.app.model.CustomerOrder;
import in.gk.app.model.OrderLineItem;

@Service
public class CustomerOrderService {

	@Autowired
	private OrderLineItemRepository orderLineItemRepo;
	@Autowired
	private CustomerOrderRepository customerOrderRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	

	
	public Iterable<CustomerOrder> getAllOrder(){
		return customerOrderRepo.findAll();
	}
	
	public Boolean deleteOrder(Integer id) {
		customerOrderRepo.delete(id);
		return true;
	}
	
	public CustomerOrder saveOrder(CustomerOrder customerOrder, List<OrderLineItem> orderLineItems, Customer customer) {
		
		Customer cust = customerRepo.save(customer);
		customerOrder.setCustomerid(cust.getId());
		CustomerOrder co = customerOrderRepo.save(customerOrder);
		for (OrderLineItem orderLineItem : orderLineItems) {
			orderLineItem.setOrderid(co.getCustomerid());
			orderLineItem.setPrice(productRepo.findOne(orderLineItem.getProductid()).getPrice());
		}
		Iterable<OrderLineItem> olt =orderLineItemRepo.save(orderLineItems);
		Double total=0.0;
		for (OrderLineItem LineItem : olt) {
			total = total+LineItem.getPrice()*LineItem.getQuantity();
		}
		co.setPrice(total);
		customerOrderRepo.save(co);
		return co;
	}
	
	public List<Object[]> getAll() {
		EntityManager session = entityManagerFactory.createEntityManager();
		
/*		String query = "Select cu.Name, cuo.status, cuo.paid, cuo.orderTime, cuo.price, ol.quantity, pr.name From CustomerOrder AS cuo INNER JOIN Customer as cu ON cu.Customer_id = cuo.Customer_id INNER JOIN OrderLineItem as ol ON ol.order_id = cuo.Order_id INNER JOIN Product as pr ON pr.Products_id = ol.Product_id";
		String query1 = "Select cu.name as cusname, cuo.status, cuo.paid, cuo.order_time, cuo.price, ol.quantity, pr.name as prodname From customer_order AS cuo INNER JOIN Customer as cu ON cu.id = cuo.customer_id INNER JOIN order_line_item as ol ON ol.orderid = cuo.id INNER JOIN Product as pr ON pr.id = ol.productid";
		String query3 = "Select cu.name as cusname, cuo.status, cuo.paid, cuo.ordertime, cuo.price, ol.quantity, pr.name as prodname From customerorder AS cuo INNER JOIN customer as cu ON cu.id = cuo.customerid INNER JOIN orderlineitem as ol ON ol.orderid = cuo.id INNER JOIN product as pr ON pr.id = ol.productid";
		
		String query2 = "Select cu.id as cid, cu.name as cname, cu.email as cemail,cu.phone as cphone from customer AS cu";
		String query4 = "Select cu.name as cname from customer AS cu";
*/		String query5 = "Select cu.id as cid, cu.name as cname, cu.email as cemail,cu.phone as cphone, "
				+ "cuo.id as coid, cuo.customerid as cocustomerid, cuo.price as coprice, cuo.paid as copaid, "
						+ "cuo.ordertime as coordertime, cuo.status as costatus,"
				+ "ol.id as olid, ol.orderid as olorderid, ol.price as olprice, ol.productid as olproductid, "
						+ "ol.quantity as olquantity, "
				+ "pr.id as pid, pr.name as pname, pr.price as pprice "
				+ "FROM customerorder AS cuo INNER JOIN customer as cu ON cu.id = cuo.customerid"
				+ " INNER JOIN orderlineitem as ol ON ol.orderid = cuo.id "
				+ " INNER JOIN product as pr ON pr.id = ol.productid "
				+ "	Where cuo.status = 'false'";
		 String query6 = "Select pr.id, pr.name, pr.price, sum(ol.quantity) as quantity " 
				 	+ "FROM customerorder AS cuo"
					+ " INNER JOIN orderlineitem as ol ON ol.orderid = cuo.id "
					+ " INNER JOIN product as pr ON pr.id = ol.productid "
					+ "	Where cuo.status = 'false' "
					+ " Group By ol.productid";
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = session.createNativeQuery(query6,"ItemCount").getResultList();
		/*System.out.println(results.get(0)[0]);
		System.out.println(results.get(0)[1]);*/
		
		session.close();
		
		return results;

	}
	
	
	
}
/*
https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
*/