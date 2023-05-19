package HoangLong.MyShoes.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.PageDTO;
import HoangLong.MyShoes.dto.ProductDTO;
import HoangLong.MyShoes.dto.searchdto.SearchDTO;
import HoangLong.MyShoes.entity.Category;
import HoangLong.MyShoes.entity.Product;
import HoangLong.MyShoes.repo.CategoryRepo;
import HoangLong.MyShoes.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo productRepo;
	@Autowired
	CategoryRepo categoryRepo;
	
	@Transactional
	public void create(ProductDTO productDTO) {
		Category category = categoryRepo.findById(productDTO.getCategory().getId()).orElseThrow(NoResultException::new);
		Product product = new ModelMapper().map(productDTO, Product.class);
		product.setCategory(category);
		productRepo.save(product);
		productDTO.setId(product.getId());
	}
	
	@Transactional
	public void update(ProductDTO productDTO) {
		Product product = productRepo.findById(productDTO.getId()).orElseThrow(NoResultException::new);
		Category category = categoryRepo.findById(productDTO.getCategory().getId()).orElseThrow(NoResultException::new);
		
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(ProductDTO.class, Product.class)
		           .addMappings(map->{map.skip(Product::setCategory); if(productDTO.getCreatedAt()==null){map.skip(Product::setCreatedAt); if(productDTO.getImage()==null){map.skip(Product::setImage);}}})
		           .setProvider(p->product);
		
		Product productUpdate = mapper.map(productDTO, Product.class);
		productUpdate.setCategory(category);
		productRepo.save(productUpdate);
	}
	
	@Transactional
	public void delete(int id) {
		productRepo.deleteById(id);
	}
	
	@Transactional
	public ProductDTO get(int id) {
		Product product = productRepo.findById(id).orElseThrow(NoResultException::new);
		ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);
		return productDTO;
	}
	
	@Transactional
	public PageDTO<ProductDTO> search(SearchDTO searchDTO){
		Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize());
		Page<Product> page = productRepo.searchByName("%" + searchDTO.getKeyword() + "%", pageable);
		
		PageDTO<ProductDTO> pageDTO = new PageDTO<ProductDTO>();
		
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		
		for (Product product : page.getContent()) {
			ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);
			productDTOs.add(productDTO);
		}
		pageDTO.setTotalElements(page.getTotalElements());
		pageDTO.setTotalPages(page.getTotalPages());
		pageDTO.setContents(productDTOs);
		
		return pageDTO;
	}
}
