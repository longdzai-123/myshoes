package HoangLong.MyShoes.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import HoangLong.MyShoes.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{ 
	
	User findByName(String name);
	
	User findByUsername(String username);
	
	
	@Query("SELECT u FROM User u WHERE u.birthdate = :x")
	List<User> searchByBirthdate(@Param("x") Date x);
	
	@Query("SELECT u FROM User u WHERE u.name LIKE :x ")
	Page<User> searchByName(@Param("x") String name, Pageable pageable);
	
	// thong ke theo thang	
	@Query("SELECT count(u.id), MONTH(u.birthdate) FROM User u GROUP BY MONTH(u.birthdate)")
	List<Object[]> thongKeTheoMonth();
	
	//tim user co ngay sinh vao hom nay 
	@Query("SELECT u FROM User u WHERE DAY(u.birthdate) = :d and MONTH(u.birthdate) = :m")
	List<User> findUserByBirthdate(@Param("d") int d, @Param("m") int m );
	
}
