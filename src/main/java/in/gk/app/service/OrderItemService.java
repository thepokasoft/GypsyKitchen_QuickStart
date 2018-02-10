package in.gk.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gk.app.dao.OrderItemRepository;
import in.gk.app.model.Order;
import in.gk.app.model.OrderItem;
import in.gk.app.model.OrderItemList;
import in.gk.app.model.Product;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepo;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	private EntityManager session;

	public Iterable<OrderItem> getAllOrderItems() {
		return orderItemRepo.findAll();
	}

	public OrderItem getSingleOrderItem(Integer id) {
		return orderItemRepo.findOne(id);
	}

	public Boolean deleteOrderItem(Integer id) {
		orderItemRepo.delete(id);
		return true;
	}

	public OrderItem saveOrderItem(OrderItem orderItem) {
		return orderItemRepo.save(orderItem);
	}

	public List<OrderItem> saveOrderItems(List<OrderItem> orderItems) {
		return orderItemRepo.save(orderItems);
	}

	public OrderItem saveOrderItem(Integer productId, Integer orderId, OrderItem orderItem) {
		orderItem.setProduct(productService.getSingleProduct(productId));
		orderItem.setOrder(orderService.getSingleOrder(orderId));
		return orderItemRepo.save(orderItem);
	}

	public List<OrderItem> saveOrderItem(Map<Integer, Integer> itemsMap, Integer orderId) {

		List<OrderItem> items = new ArrayList<OrderItem>();
		Iterator<Integer> productIds = itemsMap.keySet().iterator();

		while (productIds.hasNext()) {
			Integer productId = (Integer) productIds.next();
			Product product = productService.getSingleProduct(productId);
			int quantity = itemsMap.get(productId);
			OrderItem item = new OrderItem();
			item.setItemCurrentStatus("Not Ready");
			item.setProduct(product);
			item.setQuantity(quantity);
			item.setPrice(quantity * product.getPrice());
			item.setOrder(orderService.getSingleOrder(orderId));
			items.add(item);
		}

		return orderItemRepo.save(items);
	}

	public List<OrderItem> updateOrderItem(Map<Integer, Integer> itemsMap, Integer orderId, Order order) {

		List<OrderItem> items = new ArrayList<OrderItem>();
		Iterator<Integer> productIds = itemsMap.keySet().iterator();

		while (productIds.hasNext()) {
			Integer productId = (Integer) productIds.next();
			Product product = productService.getSingleProduct(productId);
			int quantity = itemsMap.get(productId);
			OrderItem item = new OrderItem();
			item.setProduct(product);
			item.setQuantity(quantity);
			item.setPrice(quantity * product.getPrice());
			item.setOrder(orderService.getSingleOrder(orderId));
			items.add(item);
		}

		return orderItemRepo.save(items);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getNotReadyOrderItems() {

		session = entityManagerFactory.createEntityManager();
		String query = "Select pr.name,SUM(ol.quantity) From orderlineitem AS ol INNER JOIN product AS pr ON ol.product_id = pr.id Where item_current_status ='Not Ready'"
				+ "Group By pr.name";
		List<Object[]> results = session.createNativeQuery(query).getResultList();
		session.close();
		Map<String,String> itemToProcessMap = new HashMap<>();
		for (int i = 0; i < results.size(); i++) {
			itemToProcessMap.put(results.get(i)[0].toString(), results.get(i)[1].toString());
		}

		return itemToProcessMap;

	}

	@Transactional
	public Boolean deleteItemsForOrder(Integer orderId) {
		orderItemRepo.deleteByOrderId(orderId);
		return true;
	}

}

// https://stackoverflow.com/questions/25821579/transactionrequiredexception-executing-an-update-delete-query