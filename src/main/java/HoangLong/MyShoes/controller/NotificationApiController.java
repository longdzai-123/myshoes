package HoangLong.MyShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HoangLong.MyShoes.dto.NotificationDTO;
import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.service.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationApiController {
	@Autowired
	NotificationService notificationService;
	
	@PostMapping("/notification/add")
	public ResponseDTO<NotificationDTO> create(@RequestBody NotificationDTO notificationDTO){
		notificationService.create(notificationDTO);
		return ResponseDTO.<NotificationDTO>builder().status(200).data(notificationDTO).build();
	}
	
	@PutMapping("/notification/update")
	public ResponseDTO<NotificationDTO> update(@RequestBody NotificationDTO notificationDTO){
		notificationService.update(notificationDTO);
		return ResponseDTO.<NotificationDTO>builder().status(200).data(notificationDTO).build();
	}
	
	@DeleteMapping("/notification/{id}")
	public ResponseDTO<NotificationDTO> delete(@PathVariable("id") int id){
		notificationService.delete(id);
		return ResponseDTO.<NotificationDTO>builder().status(200).build();
	}
}
