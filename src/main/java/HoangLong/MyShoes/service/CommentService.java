package HoangLong.MyShoes.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.CommentDTO;
import HoangLong.MyShoes.entity.Comment;
import HoangLong.MyShoes.repo.CommentRepo;

@Service
@Transactional
public class CommentService {
	@Autowired
	CommentRepo commentRepo;
	
	
	public void create(CommentDTO commentDTO) {
		Comment comment = new ModelMapper().map(commentDTO, Comment.class);
		commentRepo.save(comment);
		commentDTO.setId(comment.getId());
	}
	
	
	public void update(CommentDTO commentDTO) {
		Comment comment = commentRepo.findById(commentDTO.getId()).orElseThrow(NoResultException::new);
		comment.setContent(commentDTO.getContent());
		commentRepo.save(comment);
	}
	
	
	public void delete(int id) {
		commentRepo.deleteById(id);
	}
	
	public List<CommentDTO> commentByProductId(int id){
		List<Comment> comments = commentRepo.commentByProductId(id);
		
		List<CommentDTO> commentDTOs = new ArrayList<>();
		
		for (Comment comment : comments) {
			CommentDTO commentDTO = new ModelMapper().map(comment, CommentDTO.class);
			commentDTOs.add(commentDTO);
		}
		
		return commentDTOs;
	}
	
	
}
