package HoangLong.MyShoes.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private double totalPrice;
	
	@CreatedDate
	private Date buyDate;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "bill", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<BillItem> billItems;
}
