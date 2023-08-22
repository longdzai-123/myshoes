package HoangLong.MyShoes.service;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.SizeDTO;
import HoangLong.MyShoes.entity.Size;
import HoangLong.MyShoes.repo.SizeRepo;

@Service
@Transactional
public class SizeService {
	@Autowired
	SizeRepo sizeRepo;
	
	
	public void create(SizeDTO sizeDTO) {
		Size size = new ModelMapper().map(sizeDTO, Size.class);
		sizeRepo.save(size);
		sizeDTO.setId(size.getId());
	}
	
	
	public void update(SizeDTO sizeDTO) {
		 Size size = sizeRepo.findById(sizeDTO.getId()).orElseThrow(NoResultException::new); // dấu :: trong java 8 tham chiếu đến phương thức bởi tên của phương thức.
		 size.setName(sizeDTO.getName());
		 sizeRepo.save(size);
	}
	
	
	public void delete(int id) {
		sizeRepo.deleteById(id);
	}
}
