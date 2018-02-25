package in.gk.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.gk.app.model.Email_Bean;

public interface EmailRepository extends JpaRepository<Email_Bean, Integer>{

}
