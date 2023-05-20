package HoangLong.MyShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HoangLong.MyShoes.dto.ColorDTO;
import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.service.ColorService;

@RestController
@RequestMapping("/api")
public class ColorAPIController {
	@Autowired
	ColorService colorService;
	
	@PostMapping("/color/add")
	public ResponseDTO<ColorDTO> create(@RequestBody ColorDTO colorDTO){
		colorService.create(colorDTO);
		return ResponseDTO.<ColorDTO>builder().status(200).data(colorDTO).build();
	}
	
	@PutMapping("/color/update")
	public ResponseDTO<Void> update(@RequestBody ColorDTO colorDTO){
		colorService.update(colorDTO);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@DeleteMapping("/color/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		colorService.delete(id);
		return ResponseDTO.<Void>builder().status(200).build();
	}

}
