package in.gk.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import in.gk.app.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
