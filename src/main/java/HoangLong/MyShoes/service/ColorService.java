package HoangLong.MyShoes.service;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.ColorDTO;
import HoangLong.MyShoes.entity.Color;
import HoangLong.MyShoes.repo.ColorRepo;

@Service
@Transactional
public class ColorService {
	@Autowired
	ColorRepo colorRepo;
	
	
	public void create(ColorDTO colorDTO) {
		Color color = new ModelMapper().map(colorDTO, Color.class);
		colorRepo.save(color);
		colorDTO.setId(color.getId());
	}
	
	
	public void update(ColorDTO colorDTO) {
		 Color color = colorRepo.findById(colorDTO.getId()).orElseThrow(NoResultException::new); // dấu :: trong java 8 tham chiếu đến phương thức bởi tên của phương thức.
		 color.setName(colorDTO.getName());
		 colorRepo.save(color);
	}
	
	
	public void delete(int id) {
		colorRepo.deleteById(id);
	}
	

}
