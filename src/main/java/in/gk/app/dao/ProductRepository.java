package in.gk.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.gk.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
