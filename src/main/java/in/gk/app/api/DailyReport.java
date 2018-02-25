package in.gk.app.api;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.OrderRepository;
import in.gk.app.service.OrderItemService;
import in.gk.app.service.OrderService;

@RestController
@RequestMapping("reports")
public class DailyReport {

	@Autowired
	OrderService orderService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	SendEmail sendEmail;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public void getTotalOrderDetails() {

		String emailBody = "Your today's sale amount is: " + orderService.getTotalSaleAmount() + "<br><br>"
				+ "Your Sale item wise is as follows: <br><br> " + orderItemService.getTotalItemsSold();
		System.out.println(emailBody);
		try {
			sendEmail.generateAndSendEmail(emailBody);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
//https://stackoverflow.com/questions/19896870/why-is-my-spring-autowired-field-null