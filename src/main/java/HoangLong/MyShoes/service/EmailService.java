package HoangLong.MyShoes.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import HoangLong.MyShoes.dto.UserDTO;
import HoangLong.MyShoes.utils.ConstEmail;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired 
	private SpringTemplateEngine templateEngine;
	
	public void sendBirthDate(String toName, String toEmail) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
			
			Context context = new Context();
			context.setVariable("name",toName);
			
			String body = templateEngine.process(ConstEmail.TEMPLATE_FILE_NAME.BIRTHDATE, context);
			
			// send email
			helper.setTo(toEmail); // email gui toi
			helper.setText(body,true);  // nd mail
		    helper.setSubject(ConstEmail.SEND_MAIL_SUBJECT.BIRTHDATE);
		    helper.setFrom("longdybala12345@gmail.com");
		    
		    javaMailSender.send(message);
		} catch (MessagingException e) {
			log.error("Email sent with error: " + e.getMessage());
		}
	}
	
	public void sendEmailRegister(UserDTO userDTO) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
		    
			Map<String, Object> props = new HashMap<>();
			props.put("username", userDTO.getUsername());
			props.put("password", userDTO.getPassword());
			props.put("name", userDTO.getName());
			
			Context context = new Context();
			context.setVariables(props);
			String body = templateEngine.process(ConstEmail.TEMPLATE_FILE_NAME.CLIENT_REGISTER, context);
			
			// send email
			helper.setTo(userDTO.getEmail()); // email gui toi
			helper.setText(body, true); // nd mail
		    helper.setSubject(ConstEmail.SEND_MAIL_SUBJECT.CLIENT_REGISTER);
		    helper.setFrom("longdybala12345@gmail.com");
		    
		    javaMailSender.send(message);
		} catch (MessagingException e) {
			log.error("Email sent with error: " + e.getMessage());
		}
	}
}
