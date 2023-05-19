package HoangLong.MyShoes.service;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.BillDTO;
import HoangLong.MyShoes.entity.Bill;
import HoangLong.MyShoes.repo.BillRepo;

@Service
public class BillService {
	@Autowired
	BillRepo billRepo;
	
	@Transactional
	public void create(BillDTO billDTO) {
		Bill bill = new ModelMapper().map(billDTO, Bill.class);
		billRepo.save(bill);
		billDTO.setId(bill.getId());
	}
	
	@Transactional
	public void update(BillDTO billDTO) {
		Bill bill = billRepo.findById(billDTO.getId()).orElseThrow(NoResultException::new);
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(BillDTO.class, Bill.class).setProvider(p->bill);
		
		Bill billUpdate = mapper.map(billDTO, Bill.class);
		billRepo.save(billUpdate);
	}
	
	@Transactional
	public void delete(int id) {
		billRepo.deleteById(id);
	}
	
	@Transactional
	public BillDTO get(int id) {
		Bill bill = billRepo.findById(id).orElseThrow(NoResultException::new);
		BillDTO billDTO = new ModelMapper().map(bill, BillDTO.class);
		return billDTO;
	}
}
