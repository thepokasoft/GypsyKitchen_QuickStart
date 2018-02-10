package in.gk.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import in.gk.app.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByStatus(Boolean status);
	
}

/*

*/