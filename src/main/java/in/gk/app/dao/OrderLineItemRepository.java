package in.gk.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import in.gk.app.model.OrderLineItem;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Integer>{

}
