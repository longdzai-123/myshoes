package HoangLong.MyShoes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // set , get
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
	
    private int status;
    
    private String msg;
    
    private T data;
    
}
