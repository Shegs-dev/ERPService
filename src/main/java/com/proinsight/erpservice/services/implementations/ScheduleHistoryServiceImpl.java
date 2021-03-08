/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import java.util.Date;
import java.util.List;
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

import com.proinsight.erpservice.entities.ScheduleHistory;
import com.proinsight.erpservice.entities.ScheduleType;
import com.proinsight.erpservice.entities.Users;
import com.proinsight.erpservice.proxies.APIs;
import com.proinsight.erpservice.repositories.ScheduleHistoryRepository;
import com.proinsight.erpservice.repositories.ScheduleTypeRepository;
import com.proinsight.erpservice.repositories.UsersRepository;
import com.proinsight.erpservice.services.ScheduleHistoryService;

/**
 * @author OluwasegunAjayi on 4th of March 2021
 *
 */
@Service("scheduleHistoryService")
public class ScheduleHistoryServiceImpl implements ScheduleHistoryService {
	
	//private fields
	@Autowired
	ScheduleHistoryRepository scheduleHistoryRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	ScheduleTypeRepository scheduleTypeRepository;
	

	@Override
	public int add(ScheduleHistory history) {
		System.out.println("Adding Schedule History");
		
		//Validation
		if(history.getAdminID() == null || history.getAdminID().isEmpty() || history.getCandidateID() == null || history.getCandidateID().isEmpty() || history.getMeetingTime() <= 0 || 
				history.getScheduleTypeID() == null || history.getScheduleTypeID().isEmpty() || history.getStatus() < 0) return 2;
		
		try {
			Optional<Users> user = usersRepository.findById(history.getCandidateID());
			if(!user.isPresent()) return 3;
			Optional<Users> admin = usersRepository.findById(history.getAdminID());
			if(!admin.isPresent()) return 3;
			scheduleHistoryRepository.save(history);
			
			//List<ScheduleHistory> hists = this.get(history.getScheduleTypeID(), history.getCandidateID());
			if(history.getStatus() == 2) {
				System.out.println(this.sendCandidateemail(user.get().getEmail(), history.getScheduleTypeID(), history.getCandidateID()));
			}else if(history.getStatus() == 3) {
				Optional<ScheduleType> type = scheduleTypeRepository.findById(history.getScheduleTypeID());
				if(!type.isPresent()) return 4;
				System.out.println(this.sendAdminemail(admin.get().getEmail(), user.get().getFname() + " " + user.get().getLname(), type.get().getName()));
			}else if(history.getStatus() == 4) {
				//Create and Send Meeting Link
			}
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Schedule History "+e);
			return 0;
		}
	}

	@Override
	public int update(ScheduleHistory history) {
        System.out.println("Updating Schedule History");
		
		//Validation
		if(history.getAdminID() == null || history.getAdminID().isEmpty() || history.getCandidateID() == null || history.getCandidateID().isEmpty() || history.getMeetingTime() <= 0 || 
				history.getScheduleTypeID() == null || history.getScheduleTypeID().isEmpty() || history.getStatus() < 0 || history.getId() == null || history.getId().isEmpty()) return 2;
		
		try {
			Optional<ScheduleHistory> hist = scheduleHistoryRepository.findById(history.getId());
			if(!hist.isPresent()) return 3;
			Optional<Users> user = usersRepository.findById(history.getCandidateID());
			if(!user.isPresent()) return 3;
			Optional<Users> admin = usersRepository.findById(history.getAdminID());
			if(!admin.isPresent()) return 3;
			scheduleHistoryRepository.save(history);
			//List<ScheduleHistory> hists = this.get(history.getScheduleTypeID(), history.getCandidateID());
			if(history.getStatus() == 2) {
				System.out.println(this.sendCandidateemail(user.get().getEmail(), history.getScheduleTypeID(), history.getCandidateID()));
			}else if(history.getStatus() == 3) {
				Optional<ScheduleType> type = scheduleTypeRepository.findById(history.getScheduleTypeID());
				if(!type.isPresent()) return 4;
				System.out.println(this.sendAdminemail(admin.get().getEmail(), user.get().getFname() + " " + user.get().getLname(), type.get().getName()));
			}else if(history.getStatus() == 4) {
				//Create and Send Meeting Link
			}
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Schedule History "+e);
			return 0;
		}
	}

	@Override
	public List<ScheduleHistory> get(String scheduleTypeID, String candidateID) {
		System.out.println("Getting Candidate Schedule History");
		
		return scheduleHistoryRepository.findByCandidateIDAndScheduleTypeIDOrderByMeetingTimeAsc(candidateID, scheduleTypeID);
	}
	
	//Candidate Email
	private int sendCandidateemail(String email, String scheduleTypeID, String candidateID) {
		System.out.println("Sending Email");
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.office365.com");
			props.put("mail.smtp.port", "587");
			   
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
			      return new PasswordAuthentication("admin@proinsight.ca", "1484Pro#");
			   }
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("admin@proinsight.ca", false));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Olade Consulting - Candidate Meeting Schedule");
			msg.setContent("Please click <a href='"+APIs.getStaging()+"candidate-schedule.html?scheduleTypeID=" + scheduleTypeID + "&candidateID=" + candidateID + "'>here</a> to accept or decline meeting schedule. Thank you.<br>", "text/html");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("Please click <a href='"+APIs.getStaging()+"candidate-schedule.html?scheduleTypeID=" + scheduleTypeID + "&candidateID=" + candidateID + "'>here</a> to accept or decline meeting schedule. Thank you.<br>", "text/html");
			
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
	
	//Admin Email
	private int sendAdminemail(String email, String candidate, String type) {
		System.out.println("Sending Email");
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.office365.com");
			props.put("mail.smtp.port", "587");
			   
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
			      return new PasswordAuthentication("admin@proinsight.ca", "1484Pro#");
			   }
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("admin@proinsight.ca", false));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Olade Consulting - Candidate Meeting Schedule Update");
			msg.setContent("There is a new update on the meeting schedule of a Candidate. The Candidate name is "+ candidate +" and the meeting update is on " + type + ". Please Login to accept or decline. Thank you.<br>", "text/html");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("There is a new update on the meeting schedule of a Candidate. The Candidate name is "+ candidate +" and the meeting update is on " + type + ". Please Login to accept or decline. Thank you.<br>", "text/html");
			
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
	public List<ScheduleHistory> getAll() {
		// TODO Auto-generated method stub
		return scheduleHistoryRepository.findAll();
	}

	@Override
	public int submit(String scheduleTypeID, String candidateID) {
		System.out.println("Submitting Schedule History");
		return 1;
	}

}
