package HoangLong.MyShoes.service;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.CommentDTO;
import HoangLong.MyShoes.entity.Comment;
import HoangLong.MyShoes.repo.CommentRepo;

@Service
public class CommentService {
	@Autowired
	CommentRepo commentRepo;
	
	@Transactional
	public void create(CommentDTO commentDTO) {
		Comment comment = new ModelMapper().map(commentDTO, Comment.class);
		commentRepo.save(comment);
		commentDTO.setId(comment.getId());
	}
	
	@Transactional
	public void update(CommentDTO commentDTO) {
		Comment comment = commentRepo.findById(commentDTO.getId()).orElseThrow(NoResultException::new);
		comment.setContent(commentDTO.getContent());
		commentRepo.save(comment);
	}
	
	@Transactional
	public void delete(int id) {
		commentRepo.deleteById(id);
	}
	
	
}
