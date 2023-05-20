package HoangLong.MyShoes.APIcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HoangLong.MyShoes.dto.ProductDetailsDTO;
import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.service.ProductDetailsService;

@RestController
@RequestMapping("/api")
public class ProductDetailsAPIController {
	@Autowired
	ProductDetailsService productDetailsService;
	
	@PostMapping("/productdetails/add")
	public ResponseDTO<ProductDetailsDTO> create(@RequestBody ProductDetailsDTO productDetailsDTO){
		productDetailsService.create(productDetailsDTO);
		return ResponseDTO.<ProductDetailsDTO>builder().status(200).data(productDetailsDTO).build();
	}
	
	@PutMapping("/productdetails/update")
	public ResponseDTO<Void> update(@RequestBody ProductDetailsDTO productDetailsDTO){
		productDetailsService.update(productDetailsDTO);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@DeleteMapping("/productdetails/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		productDetailsService.delete(id);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@GetMapping("/productdetails/{id}")
	public ResponseDTO<ProductDetailsDTO> get(@PathVariable("id") int id){
		ProductDetailsDTO productDetailsDTO = productDetailsService.get(id);
		return ResponseDTO.<ProductDetailsDTO>builder().status(200).data(productDetailsDTO).build();
	}
}
