package HoangLong.MyShoes.jobs;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import HoangLong.MyShoes.entity.User;
import HoangLong.MyShoes.repo.UserRepo;
import HoangLong.MyShoes.service.EmailService;

@Component
public class BirthDateJob {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	EmailService emailService;
	
	@Scheduled(cron = "*/20 * * * * *")
	public void sendEmailBirthdate() {
		Calendar calendar = Calendar.getInstance();
		List<User> users = userRepo.findUserByBirthdate(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH)+1);
		for (User user : users) {
			emailService.sendBirthDate(user.getName(), user.getEmail());
		} 
	}
}
