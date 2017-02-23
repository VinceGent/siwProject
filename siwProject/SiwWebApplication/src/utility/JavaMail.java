package utility;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import elements.AuctionOffer;
import elements.Insertion;
 
public class JavaMail {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	
	
	
	public static void sendMail(String dest,
			String testoEmail) throws MessagingException, AddressException {
		String oggetto="VCPbay";
		
		
	
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
		// Step2

		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
		generateMailMessage.setSubject(oggetto);
		generateMailMessage.setContent(testoEmail, "text/html");

 
		// Step3
		Transport transport = getMailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com","vcpbay@gmail.com", "password93");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	
	
	}
	public static void main(String[] args) throws AddressException, MessagingException {
//	JavaMail.sendMail("carlo.rocca92@gmail.com"," ciao carlo la cacca ");
	
		
	}
	public static String Buyer(Insertion insertion) {
		
		return " Ti sei aggiudicato l'asta per "+insertion.getName();
	}
	public static String Seller(Insertion insertion,  float offer) {
		return "Il tuo oggetto "+insertion.getName() +"è stato acquistato al prezzo di "+offer +" euro";
	}
 
}