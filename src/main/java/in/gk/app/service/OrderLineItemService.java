package in.gk.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gk.app.dao.OrderLineItemRepository;
import in.gk.app.model.OrderLineItem;

@Service
public class OrderLineItemService {

	@Autowired
	private OrderLineItemRepository orderLineItemRepo;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public Iterable<OrderLineItem> getAllOrderLineItem(){
		return orderLineItemRepo.findAll();
	}
	
	public Boolean deleteOrderLineItem(Integer id) {
		orderLineItemRepo.delete(id);
		return true;
	}

	public Iterable<OrderLineItem> saveOrderLineItem(Integer customerOrderID, List<OrderLineItem> orderLineItems) {
		
		for (OrderLineItem orderLineItem : orderLineItems) {
			orderLineItem.setOrderid(customerOrderID);
		}
		return orderLineItemRepo.save(orderLineItems);
		
	}
	
	public List<Object[]> getOrderLineItemforActiveOrders() {
		EntityManager session = entityManagerFactory.createEntityManager();
	
		String query8 = "Select "
				+ "cuo.id as coid, cuo.customerid as cocustomerid, cuo.price as coprice, cuo.paid as copaid, "
						+ "cuo.ordertime as coordertime, cuo.orderfinishtime as coorderfinishtime, cuo.status as costatus,"
				+ "ol.id as olid, ol.orderid as olorderid, ol.price as olprice, ol.productid as olproductid, "
						+ "ol.quantity as olquantity, "
				+ "pr.id as pid, pr.name as pname, pr.price as pprice "
				+ "FROM customerorder AS cuo INNER JOIN customer as cu ON cu.id = cuo.customerid"
				+ " INNER JOIN orderlineitem as ol ON ol.orderid = cuo.id "
				+ " INNER JOIN product as pr ON pr.id = ol.productid "
				+ "	Where cuo.status = 'false'";

		@SuppressWarnings("unchecked")
		List<Object[]> results = session.createNativeQuery(query8,"orderItemMapping").getResultList();
		
		session.close();
		
		return results;
	}
}
