package com.example.MH3LopMxh.repository;

import com.example.MH3LopMxh.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Query("SELECT c FROM Comment c JOIN c.postComment pc WHERE pc.post.idPost = :postId ORDER BY c.createAt ASC")
    List<Comment> findByPostIdOrderByCreateAtAsc(@Param("postId") Long postId);
    
    List<Comment> findByUserSendIdUser(Long userId);
    
    @Query("SELECT COUNT(c) FROM Comment c JOIN c.postComment pc WHERE pc.post.idPost = :postId")
    Long countByPostId(@Param("postId") Long postId);
}
