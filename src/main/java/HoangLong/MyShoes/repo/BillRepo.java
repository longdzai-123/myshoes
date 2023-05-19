package HoangLong.MyShoes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import HoangLong.MyShoes.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{
     
}
