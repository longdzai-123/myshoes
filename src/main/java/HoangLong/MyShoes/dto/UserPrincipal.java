package HoangLong.MyShoes.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserPrincipal extends User{
    
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private List<String> roles;
	
	public UserPrincipal(String username, String password,Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
}
