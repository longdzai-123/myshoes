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

import HoangLong.MyShoes.dto.CategoryDTO;
import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryAPIController {
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/admin/category/add")
	public ResponseDTO<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO){
		categoryService.create(categoryDTO);
		return ResponseDTO.<CategoryDTO>builder().status(200).data(categoryDTO).build();
	}
	
	@PutMapping("/admin/category/update")
	public ResponseDTO<Void> update(@RequestBody CategoryDTO categoryDTO){
		categoryService.update(categoryDTO);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@DeleteMapping("/admin/category/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		categoryService.delete(id);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@GetMapping("/category/{id}")
	public ResponseDTO<CategoryDTO> get(@PathVariable("id") int id){
		CategoryDTO categoryDTO = categoryService.get(id);
		return ResponseDTO.<CategoryDTO>builder().status(200).data(categoryDTO).build();
	}
}
