package HoangLong.MyShoes.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommentDTO {
	private Integer id;
	
	private String content;
	
	private ProductDTO product;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm",timezone = "Asia/Ho_Chi_Minh")
	private Date cmtDate;
	
	private UserDTO user;
}
