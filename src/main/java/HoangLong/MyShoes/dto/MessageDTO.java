package HoangLong.MyShoes.dto;

import lombok.Data;

@Data
public class MessageDTO {
	private String toName;
	private String toEmail;
	private String subject;
	private String content;
}
