package in.gk.app.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.EmailRepository;
import in.gk.app.model.Email_Bean;

@RestController
@RequestMapping(value="/api/email")
public class EmailConfigs {

	@Autowired
	EmailRepository emailRepo;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public void setEmail(@RequestBody Email_Bean email)
	{
		emailRepo.save(email);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<Email_Bean> getEmail()
	{
		return null;
	}
}
