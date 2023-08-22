package HoangLong.MyShoes.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import HoangLong.MyShoes.utils.FileStore;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductRepo productRepo;
	@Autowired
	CategoryRepo categoryRepo;
	
	
	public void create(ProductDTO productDTO) {
		Category category = categoryRepo.findById(productDTO.getCategory().getId()).orElseThrow(NoResultException::new);
		Product product = new ModelMapper().map(productDTO, Product.class);
		product.setCategory(category);
		productRepo.save(product);
		productDTO.setId(product.getId());
	}
	
	
	@CacheEvict(cacheNames = "products", allEntries = true)
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
	
	
	@CacheEvict(cacheNames = "products", allEntries = true)
	public void delete(int id) {
		Product product = productRepo.findById(id).orElseThrow(NoResultException::new);
		String filename = product.getImage();
		FileStore.deleteFile(filename);
		productRepo.deleteById(id);
	}
	
	
	public ProductDTO get(int id) {
		Product product = productRepo.findById(id).orElseThrow(NoResultException::new);
		ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);
		return productDTO;
	}
	
	
	@Cacheable(cacheNames = "products")
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
	
	public List<ProductDTO> searchAll(){
		List<Product> products = productRepo.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	
	public List<ProductDTO> searchByName(String keyword){
		List<Product> products = productRepo.searchByName("%"+keyword+"%");
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
}
