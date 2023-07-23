package HoangLong.MyShoes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import HoangLong.MyShoes.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{
     // thong ke so luong bill theo thang 
	@Query("SELECT count(b.id),month(b.buyDate),year(b.buyDate) FROM Bill as b group by month(b.buyDate),year(b.buyDate)")
	List<Object[]> thongkebilltheothang();
	
	@Query("SELECT b FROM Bill b JOIN b.user u WHERE u.id = :id")
	List<Bill> billByUserId(@Param("id") int id);
	
}
