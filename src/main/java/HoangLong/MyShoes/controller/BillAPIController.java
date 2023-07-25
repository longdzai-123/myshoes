package HoangLong.MyShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HoangLong.MyShoes.dto.BillDTO;
import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.dto.UserDTO;
import HoangLong.MyShoes.dto.UserPrincipal;
import HoangLong.MyShoes.dto.statistic.BillStatistic;
import HoangLong.MyShoes.service.BillService;
import HoangLong.MyShoes.service.UserService;

@RestController
@RequestMapping("/api")
public class BillAPIController {
	@Autowired
	BillService billService;
	@Autowired
	UserService userService;
	
	@PostMapping("/member/bill/add")
	public ResponseDTO<BillDTO> create(@RequestBody BillDTO billDTO){
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDTO userDTO = userService.getById(userPrincipal.getId());
		billDTO.setUser(userDTO);
		billService.create(billDTO);
		return ResponseDTO.<BillDTO>builder().status(200).data(billDTO).build();
	}
	
	@PutMapping("/member/bill/update")
	public ResponseDTO<BillDTO> update(@RequestBody BillDTO billDTO){
		billService.update(billDTO);
		return ResponseDTO.<BillDTO>builder().status(200).data(billDTO).build();
	}
	
	@DeleteMapping("/member/bill/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
	     billService.delete(id);
	     return ResponseDTO.<Void>builder().status(200).build();
	}
	
//	@GetMapping("/member/bill/{id}")
//	public ResponseDTO<BillDTO> get(@PathVariable("id") int id){
//		BillDTO billDTO = billService.get(id);
//	     return ResponseDTO.<BillDTO>builder().status(200).data(billDTO).build();
//	}
	
	@GetMapping("/admin/bill/statistic")
	public List<BillStatistic> thongke(){
		return billService.thongKeTheoThang();
	}
	
	@GetMapping("/member/bill/{id}")
	public List<BillDTO> billByUserId(@PathVariable("id") int id){
		return billService.billByUserId(id);
	}
}
