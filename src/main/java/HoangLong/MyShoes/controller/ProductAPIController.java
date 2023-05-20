package HoangLong.MyShoes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HoangLong.MyShoes.dto.PageDTO;
import HoangLong.MyShoes.dto.ProductDTO;
import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.dto.searchdto.SearchDTO;
import HoangLong.MyShoes.service.ProductService;
import HoangLong.MyShoes.utils.FileStore;

@RestController
@RequestMapping("/api")
public class ProductAPIController {
	
	@Autowired
	ProductService productService;
	
	
	@PostMapping("/product/add")
	public ResponseDTO<ProductDTO> create(@ModelAttribute @Valid ProductDTO productDTO){
		productDTO.setImage(FileStore.getFileName(productDTO.getMultipartFile(), "product-"));
		productService.create(productDTO);
		return ResponseDTO.<ProductDTO>builder().status(200).data(productDTO).build();
	}
	
	@PutMapping("/product/update")
	public ResponseDTO<Void> update(@ModelAttribute @Valid ProductDTO productDTO){
		productDTO.setImage(FileStore.getFileName(productDTO.getMultipartFile(), "product-"));
		productService.update(productDTO);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		productService.delete(id);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@GetMapping("/product/{id}")
	public ResponseDTO<ProductDTO> get(@PathVariable("id") int id){
		ProductDTO productDTO = productService.get(id);
		return ResponseDTO.<ProductDTO>builder().status(200).data(productDTO).build();
	}
	
	@GetMapping("/product/search")
	public ResponseDTO<PageDTO<ProductDTO>> search(@RequestBody SearchDTO searchDTO){
		if(searchDTO.getPage() == null) {
			 searchDTO.setPage(0);
		}
		if(searchDTO.getSize() == null) {
			searchDTO.setSize(10);
		}
		if(searchDTO.getKeyword() == null) {
			searchDTO.setKeyword("");
		}
		PageDTO<ProductDTO> pageDTO = productService.search(searchDTO);
		return ResponseDTO.<PageDTO<ProductDTO>>builder().status(200).data(pageDTO).build();
		
	}
}
