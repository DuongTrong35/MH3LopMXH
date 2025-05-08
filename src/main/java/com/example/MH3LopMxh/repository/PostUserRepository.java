package com.example.MH3LopMxh.repository;

import com.example.MH3LopMxh.model.PostUser;
import com.example.MH3LopMxh.model.Post;
import com.example.MH3LopMxh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostUserRepository extends JpaRepository<PostUser, Long> {
    
    Optional<PostUser> findByPost(Post post);
    
    List<PostUser> findByUser(User user);

    List<PostUser> findAllByPost(Post post);
}

