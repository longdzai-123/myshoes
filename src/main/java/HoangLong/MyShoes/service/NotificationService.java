package HoangLong.MyShoes.service;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.NotificationDTO;
import HoangLong.MyShoes.entity.Notification;
import HoangLong.MyShoes.repo.NotificationRepo;

@Service
@Transactional
public class NotificationService {
	@Autowired
	NotificationRepo notificationRepo;
	
	
	public void create(NotificationDTO notificationDTO) {
		Notification notification = new ModelMapper().map(notificationDTO, Notification.class);
		notificationRepo.save(notification);
		notificationDTO.setId(notification.getId());
	}
	
	
	public void update(NotificationDTO notificationDTO) {
		Notification notification = notificationRepo.findById(notificationDTO.getId()).orElseThrow(NoResultException::new);
		notification.setContent(notificationDTO.getContent());
		notification.setSeen(notificationDTO.isSeen());
		notification.setToUser(notificationDTO.getToUser());
		notificationRepo.save(notification);
	}
	
	
	public void delete(int id) {
		notificationRepo.deleteById(id);
	}
}
