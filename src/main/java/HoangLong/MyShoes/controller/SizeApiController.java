package HoangLong.MyShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.dto.SizeDTO;
import HoangLong.MyShoes.service.SizeService;

@RestController
@RequestMapping("/api")
public class SizeApiController {
	@Autowired
	SizeService sizeService;
	
	@PostMapping("/size/add")
	public ResponseDTO<SizeDTO> create(@RequestBody SizeDTO sizeDTO){
		sizeService.create(sizeDTO);
		return ResponseDTO.<SizeDTO>builder().status(200).data(sizeDTO).build();
	}
	
	@PutMapping("/size/update")
	public ResponseDTO<Void> update(@RequestBody SizeDTO sizeDTO){
		sizeService.update(sizeDTO);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@DeleteMapping("/size/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		sizeService.delete(id);
		return ResponseDTO.<Void>builder().status(200).build();
	}

}
