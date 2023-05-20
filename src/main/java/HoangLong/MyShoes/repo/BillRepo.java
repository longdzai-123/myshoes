package HoangLong.MyShoes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import HoangLong.MyShoes.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{
     // thong ke so luong bill theo thang 
	@Query("SELECT count(b.id),month(b.buyDate),year(b.buyDate) FROM Bill as b group by month(b.buyDate),year(b.buyDate)")
	List<Object[]> thongkebilltheothang();
	
}
