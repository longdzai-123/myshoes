package HoangLong.MyShoes.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
public class BillDTO {
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date buyDate;
	
    private String name;
	
	private String address;
	
	private String phone;
	
	private double totalPrice;
	
	private UserDTO user;
	
	@JsonManagedReference
	private List<BillItemDTO> billItems;
	
}
