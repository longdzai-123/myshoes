package HoangLong.MyShoes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import HoangLong.MyShoes.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
  @Query("SELECT c FROM Comment c JOIN c.product p WHERE p.id = :id")
  List<Comment> commentByProductId(@Param("id") int id);
}
