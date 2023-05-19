package HoangLong.MyShoes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import HoangLong.MyShoes.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
