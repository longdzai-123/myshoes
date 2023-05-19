package HoangLong.MyShoes.dto;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
public class BillItemDTO {
	
	private Integer id;
	
	@JsonBackReference
	private BillDTO bill;
	
	private ProductDTO product;
	
	@Min(0)
	private long quantity; 
	
	@Min(0)
	private double price;
	
	
}
