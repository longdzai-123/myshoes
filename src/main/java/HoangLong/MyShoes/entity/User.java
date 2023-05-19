package HoangLong.MyShoes.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotEmpty(message = "not.empty")
	private String name;
	
	private String avatar; // URL
	
	@NotEmpty(message = "not.empty")
	@Column(unique = true)
	private String username;
	
	@NotEmpty(message = "not.empty")
	private String password;
	
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@ElementCollection
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"))
	@Column(name = "role")
	private List<String> roles;
	
	
	
}
