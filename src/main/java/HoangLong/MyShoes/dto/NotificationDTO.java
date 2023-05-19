package HoangLong.MyShoes.dto;

import HoangLong.MyShoes.entity.User;
import lombok.Data;

@Data
public class NotificationDTO {
	private Integer id;
	
	private String content;
	
	private User toUser;
	
	private boolean seen;
}
