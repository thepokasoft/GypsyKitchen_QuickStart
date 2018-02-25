package in.gk.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Email_Bean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer emailid;

	String emailfrom;
	String emailpassword;
	String emailto;
	String emailcc;
	public Integer getEmailid() {
		return emailid;
	}
	public void setEmailid(Integer emailid) {
		this.emailid = emailid;
	}
	public String getEmailfrom() {
		return emailfrom;
	}
	public void setEmailfrom(String emailfrom) {
		this.emailfrom = emailfrom;
	}
	public String getEmailpassword() {
		return emailpassword;
	}
	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}
	public String getEmailto() {
		return emailto;
	}
	public void setEmailto(String emailto) {
		this.emailto = emailto;
	}
	public String getEmailcc() {
		return emailcc;
	}
	public void setEmailcc(String emailcc) {
		this.emailcc = emailcc;
	}

}
