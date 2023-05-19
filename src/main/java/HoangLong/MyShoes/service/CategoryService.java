package HoangLong.MyShoes.service;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.CategoryDTO;
import HoangLong.MyShoes.entity.Category;
import HoangLong.MyShoes.repo.CategoryRepo;

@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    
    public void create(CategoryDTO categoryDTO) {
    	Category category = new ModelMapper().map(categoryDTO, Category.class);
    	categoryRepo.save(category);
    	categoryDTO.setId(category.getId());
    }
    
    public void update(CategoryDTO categoryDTO) {
    	Category category = categoryRepo.findById(categoryDTO.getId()).orElseThrow(NoResultException::new);
    	category.setName(categoryDTO.getName());
    	categoryRepo.save(category);
    }
    
    public void delete(int id) {
    	categoryRepo.deleteById(id);
    }
    
    public CategoryDTO get(int id) {
    	Category category = categoryRepo.findById(id).orElseThrow(NoResultException::new);
    	CategoryDTO categoryDTO = new ModelMapper().map(category, CategoryDTO.class);
    	return categoryDTO;
    }
    
    
}
