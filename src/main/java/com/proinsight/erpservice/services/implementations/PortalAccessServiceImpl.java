/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.entities.PortalAccess;
import com.proinsight.erpservice.entities.Users;
import com.proinsight.erpservice.proxies.EmailCorrespondence;
import com.proinsight.erpservice.repositories.PortalAccessRepository;
import com.proinsight.erpservice.repositories.UsersRepository;
import com.proinsight.erpservice.services.PortalAccessService;
import com.proinsight.erpservice.utils.HashPassword;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Service("portalAccessService")
public class PortalAccessServiceImpl implements PortalAccessService {
	
	//private fields
	@Autowired
	PortalAccessRepository portalAccessRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	HashPassword hashPassword;

	@Override
	public int add(PortalAccess access) {
		System.out.println("Adding Portal Access");
		
		//Validation
		if(access.getCandidateID() == null || access.getCandidateID().isEmpty() || (access.getStatus() < 0 && access.getStatus() > 1)) return 2;
		
		try {
			PortalAccess acc = portalAccessRepository.findByCandidateID(access.getCandidateID());
			if(acc != null) return 3;
			portalAccessRepository.save(access);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Portal Access "+e);
			return 0;
		}
	}

	@Override
	public int update(PortalAccess access) {
        System.out.println("Updating Portal Access");
		
		//Validation
		if(access.getCandidateID() == null || access.getCandidateID().isEmpty() || (access.getStatus() < 0 && access.getStatus() > 1)) return 2;
		
		try {
			PortalAccess acc = portalAccessRepository.findByCandidateID(access.getCandidateID());
			if(acc != null) {
				if(access.getId() == null || access.getId().isEmpty()) return 3;
				
				//If Granted Access before but Not Anymore
				if(acc.getStatus() == 1 && access.getStatus() == 0) return 5;
				
				//If Granted Access then Update Candidate
				if(acc.getStatus() == 0 && access.getStatus() == 1) {
					Optional<Users> user = usersRepository.findById(access.getCandidateID());
					if(!user.isPresent()) {
						return 4;
					}
					user.get().setUsername(user.get().getEmail());
					user.get().setPassword(hashPassword.hashPass("Robin"));
					usersRepository.save(user.get());
					System.out.println(this.sendmail(user.get().getEmail()));
				}
			}
			portalAccessRepository.save(access);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Portal Access "+e);
			return 0;
		}
	}

	@Override
	public PortalAccess getByCandidateID(String candidateID) {
		System.out.println("Getting By Candidate ID");
		
		return portalAccessRepository.findByCandidateID(candidateID);
	}
	
	private int sendmail(String email) {
		System.out.println("Sending Email");
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			   
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
			      return new PasswordAuthentication(EmailCorrespondence.getEmail(), EmailCorrespondence.getPassword());
			   }
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(EmailCorrespondence.getEmail(), false));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Olade Consulting - Candidate Portal Access");
			msg.setContent("You have been granted access to the portal. To Login your username is your email address and your password is Robin. Thank you.<br>", "text/html");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("You have been granted access to the portal. To Login your username is your email address and your password is Robin. Thank you.<br>", "text/html");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			//MimeBodyPart attachPart = new MimeBodyPart();

			//attachPart.attachFile("/var/tmp/image19.png");
			//multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);
			
			return 1;
		}catch(MessagingException e) {
			System.err.println("Error While Sending Mail "+e);
			return 0;
		}
		
		   
	}

}
