package HoangLong.MyShoes.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ProductDTO {
    
	private Integer id;
	
	@NotBlank
	private String name;
	
	@Min(0)
	private double price;
	
	private String description;
	
	private String image;
	
	private CategoryDTO category;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm",timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	
	@JsonIgnore
	private MultipartFile multipartFile;
	
}
