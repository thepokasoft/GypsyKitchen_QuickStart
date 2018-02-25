package in.gk.app.api;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import in.gk.app.dao.EmailRepository;
import in.gk.app.model.Email_Bean;

@RestController
public class SendEmail {

	@Autowired
	EmailRepository emailRepo;

	public void generateAndSendEmail(String emailBody) throws MessagingException {

		Email_Bean email = emailRepo.findOne(1);

		Properties mailServerProperties;
		Session getMailSession;
		MimeMessage generateMailMessage;
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmailto()));
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmailcc()));
		generateMailMessage.setSubject("Gypsy Kitchen {Your Daily Report is ready}");
		emailBody = emailBody + "<br><br> Regards, <br>Gypsy IT Team";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");

		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", email.getEmailfrom(), email.getEmailpassword());
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();

	}
}

// https://crunchify.com/java-mailapi-example-send-an-email-via-gmail-smtp/
//https://github.com/google/gmail-oauth2-tools/wiki/JavaSampleCode