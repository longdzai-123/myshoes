package HoangLong.MyShoes.dto.searchdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchUserDTO  {
	
	private String keyword;
	
	private Integer page;
	
	private Integer size;
  
}
