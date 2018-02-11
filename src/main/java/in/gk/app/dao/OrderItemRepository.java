package in.gk.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import in.gk.app.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	long deleteByOrderId(Integer orderid);
	List<OrderItem> findByOrderId(Integer orderid);

}
