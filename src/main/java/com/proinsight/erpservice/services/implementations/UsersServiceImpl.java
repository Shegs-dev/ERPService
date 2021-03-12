/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.proinsight.erpservice.dtos.LoginDTO;
import com.proinsight.erpservice.dtos.ResponseDTO;
import com.proinsight.erpservice.dtos.UserFilterDTO;
import com.proinsight.erpservice.entities.PortalAccess;
import com.proinsight.erpservice.entities.Users;
import com.proinsight.erpservice.proxies.APIs;
import com.proinsight.erpservice.proxies.EmailCorrespondence;
import com.proinsight.erpservice.repositories.UsersRepository;
import com.proinsight.erpservice.services.UsersService;
import com.proinsight.erpservice.utils.DateConverter;
import com.proinsight.erpservice.utils.HashPassword;

/**
 * @author OluwasegunAjayi on 22nd of February 2021
 *
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
	
	//private fields
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	PortalAccessServiceImpl portalAccessServiceImpl;
	@Autowired
	DateConverter dateConverter;
	@Autowired
	HashPassword hashPassword;

	@Override
	public int add(Users user) {
		System.out.println("Adding/Inviting User");
		
		//Validation
		if(user.getEmail() == null || user.getEmail().isEmpty() || user.getFname() == null || user.getFname().isEmpty() 
				|| user.getLname() == null || user.getLname().isEmpty() || user.getType() == 0)
			return 2;
		if(user.getType() < 1 || user.getType() > 3) return 3;
		
		try {
			Users usr = usersRepository.findByUsernameOrEmail("", user.getEmail());
			if(usr != null) return 4;
			user.setCreatedTime(dateConverter.getCurrentTimestamp());
			usersRepository.save(user);
			
			//Sending Email
			Users cusr = usersRepository.findByUsernameOrEmail("", user.getEmail());
			System.out.println(this.sendmail(user.getEmail(), cusr.getId(), user.getType()));
			
			//Adding Portal Access
			PortalAccess access = new PortalAccess();
			access.setCandidateID(cusr.getId());
			access.setStatus(0);
			//System.out.println(portalAccessServiceImpl.add(access));
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding/Inviting User "+e);
			return 0;
		}
	}

	@Override
	public int completeReg(Users user) {
		System.out.println("Completing User Registration");
		
		//Validation
		if(user.getCountryOrRegion() == null || user.getCountryOrRegion().isEmpty() || user.getEmail() == null || user.getEmail().isEmpty() || user.getFname() == null || user.getFname().isEmpty() 
				|| user.getFullStreetAddress() == null || user.getFullStreetAddress().isEmpty() || user.getLname() == null || user.getLname().isEmpty()	|| user.getPhone() == null || user.getPhone().isEmpty() 
				|| user.getPostalCode() == null || user.getProvince() == null || user.getProvince().isEmpty() || user.getTown() == null || user.getTown().isEmpty() || user.getType() == 0 || user.getId() == null 
				|| user.getId().isEmpty())
			return 2;
		if(user.getType() < 1 || user.getType() > 3) return 3;
		if(user.getType() == 2 || user.getType() == 3) {
			if(user.getPassword() == null || user.getPassword().isEmpty() || user.getUsername() == null || user.getUsername().isEmpty()) return 2;
		}
		
		try {
			Users usr = usersRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
			if(usr != null) {
				if(!usr.getId().equals(user.getId())) return 4;
			}
			if(user.getPassword() != null && !user.getPassword().isEmpty()) user.setPassword(hashPassword.hashPass(user.getPassword()));
			usersRepository.save(user);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Completing User Registration "+e);
			return 0;
		}
	}

	@Override
	public ResponseDTO doLogin(LoginDTO loginDTO) {
		System.out.println("Do Login");
		
		ResponseDTO response = new ResponseDTO();
		//Validation
		if(loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty() || loginDTO.getUsername() == null || loginDTO.getUsername().isEmpty()) {
			response.setStatus("EMPTY_TEXTFIELD");
			response.setMessage("Fill Empty Textfield(s)");
			return response;
		}
		
		try {
			Users user = usersRepository.findByUsernameOrEmail(loginDTO.getUsername(), loginDTO.getUsername());
			if(user == null) {
				response.setStatus("ACCOUNT_NONEXISTS");
				response.setMessage("User Account Does Not Exist!");
				return response;
			}
			
			if(!hashPassword.validatePass(loginDTO.getPassword(), user.getPassword())) {
				response.setStatus("INVALID_CREDENTIALS");
				response.setMessage("Username Or Password Incorrect!");
				return response;
			}
			
			response.setStatus("SUCCESS");
			response.setMessage("Login Successful");
			response.setData(user);
			return response;
		}catch(Exception e) {
			System.err.println("Error While Do Login "+e);
			response.setStatus("FAILURE");
			response.setMessage("Do Login Failed");
			return response;
		}
	}

	@Override
	public List<Users> get(int type) {
		System.out.println("Getting Users");
		
		if(type == 0) return usersRepository.findAll();
		return usersRepository.findByTypeOrderByCreatedDateDesc(type);
	}

	@Override
	public List<Users> find(UserFilterDTO filterDTO) {
		System.out.println("Finding Users");
		
		List<Users> users = this.get(filterDTO.getType());
		List<Users> retUsers = new ArrayList<>();
		for(Users user : users) {
			int checkValue = 0;
			if(filterDTO.getEmail() != null && !filterDTO.getEmail().isEmpty()) {
				if(!user.getEmail().equalsIgnoreCase(filterDTO.getEmail())) checkValue = 1;
			}
			if(filterDTO.getFname() != null && !filterDTO.getFname().isEmpty()) {
				if(!user.getFname().equalsIgnoreCase(filterDTO.getFname())) checkValue = 1;
			}
			if(filterDTO.getLname() != null && !filterDTO.getLname().isEmpty()) {
				if(!user.getLname().equalsIgnoreCase(filterDTO.getLname())) checkValue = 1;
			}
			if(filterDTO.getProgram() != null && !filterDTO.getProgram().isEmpty()) {
				if(!user.getProgram().equalsIgnoreCase(filterDTO.getProgram())) checkValue = 1;
			}
			
			if(checkValue == 0) retUsers.add(user);
		}
		
		return retUsers;
	}

	@Override
	public List<Users> getByIDs(List<String> ids) {
		System.out.println("Getting By IDs");
		
		return usersRepository.findByIdInOrderByCreatedTimeDesc(ids);
	}
	
	private int sendmail(String email, String id, int type) {
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
			if(type == 1) {
				msg.setSubject("Olade Consulting - Candidate Invite");
				msg.setContent("Please click <a href='"+APIs.getStaging()+"csignup.html?id=" + id + "'>here</a> to accept invitation. Thank you.<br>", "text/html");
			}else {
				msg.setSubject("Olade Consulting - Admin Invite");
				msg.setContent("Please click <a href='"+APIs.getStaging()+"asignup.html?id=" + id + "'>here</a> to accept invitation. Thank you.<br>", "text/html");
			}
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			if(type == 1) {
				messageBodyPart.setContent("Please click <a href='"+APIs.getStaging()+"csignup.html?id=" + id + "'>here</a> to accept invitation. Thank you.<br>", "text/html");
			}else {
				messageBodyPart.setContent("Please click <a href='"+APIs.getStaging()+"asignup.html?id=" + id + "'>here</a> to accept invitation. Thank you.<br>", "text/html");
			}
			
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

	@Override
	public int delete(String email) {
		Users user = usersRepository.findByUsernameOrEmail("", email);
		usersRepository.delete(user);
		return 1;
	}

}
