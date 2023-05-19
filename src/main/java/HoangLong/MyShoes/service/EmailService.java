package HoangLong.MyShoes.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendBirthDate(String toName, String toEmail) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
		    
			// send email
			helper.setTo(toEmail); // email gui toi
			helper.setText("Gửi lời chúc mừng  tốt đẹp nhất đến Quý khách hàng nhân ngày sinh nhật. Kính chúc Quý khách vui vẻ, hạnh phúc gặp nhiều may mắn và thành công trong cuộc sống. Cảm ơn Quý khách đã luôn đồng hành và ủng hộ công ty trong thời gian qua");
		    helper.setSubject("Chúc mừng sinh nhật");
		    
		    javaMailSender.send(message);
		} catch (MessagingException e) {
			log.error("Email sent with error: " + e.getMessage());
		}
	}
}
