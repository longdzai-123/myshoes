package HoangLong.MyShoes.dto;

import lombok.Data;

@Data
public class ProductDetailsDTO {
	
	private Integer id;
	
	private ProductDTO product;
	
	private SizeDTO size;
	
	private ColorDTO color;
	
	private long quantity;
}
