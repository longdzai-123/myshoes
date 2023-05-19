package HoangLong.MyShoes.service;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.ProductDetailsDTO;
import HoangLong.MyShoes.entity.ProductDetails;
import HoangLong.MyShoes.repo.ProductDetailsRepo;

@Service
public class ProductDetailsService {
	@Autowired
	ProductDetailsRepo productDetailsRepo;
	
	@Transactional
	public void create(ProductDetailsDTO productDetailsDTO) {
		ProductDetails productDetails = new ModelMapper().map(productDetailsDTO, ProductDetails.class);
		productDetailsRepo.save(productDetails);
	}
	
	@Transactional
	public void update(ProductDetailsDTO productDetailsDTO) {
		ProductDetails productDetails = productDetailsRepo.findById(productDetailsDTO.getId()).orElseThrow(NoResultException::new);
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(ProductDetailsDTO.class, ProductDetails.class).setProvider(p->productDetails);
		ProductDetails productDetailsUpdate = mapper.map(productDetailsDTO, ProductDetails.class);
		productDetailsRepo.save(productDetailsUpdate);
	}
	
	@Transactional
	public void delete(int id) {
		productDetailsRepo.deleteById(id);
	}
	
	@Transactional
	public ProductDetailsDTO get(int id) {
		ProductDetails productDetails = productDetailsRepo.findById(id).orElseThrow(NoResultException::new);
		ProductDetailsDTO productDetailsDTO = new ModelMapper().map(productDetails, ProductDetailsDTO.class);
		return productDetailsDTO;
	}
}
