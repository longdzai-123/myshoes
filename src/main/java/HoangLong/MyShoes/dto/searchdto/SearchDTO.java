package HoangLong.MyShoes.dto.searchdto;

import lombok.Data;

@Data
public class SearchDTO {
	
	private Integer page;
	
	private Integer size;
	
	private String keyword;
	
}
