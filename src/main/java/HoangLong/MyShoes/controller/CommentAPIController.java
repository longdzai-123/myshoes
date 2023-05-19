package HoangLong.MyShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HoangLong.MyShoes.dto.CommentDTO;
import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentAPIController {
	@Autowired
	CommentService commentService;
	
	@PostMapping("/member/comment/add")
	public ResponseDTO<CommentDTO> create(@RequestBody CommentDTO commentDTO){
		commentService.create(commentDTO);
		return ResponseDTO.<CommentDTO>builder().status(200).data(commentDTO).build();
	}
	
	@PutMapping("/member/comment/update")
	public ResponseDTO<Void> update(@RequestBody CommentDTO commentDTO){
		commentService.update(commentDTO);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@DeleteMapping("/member/comment/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		commentService.delete(id);
		return ResponseDTO.<Void>builder().status(200).build();
	}
}
