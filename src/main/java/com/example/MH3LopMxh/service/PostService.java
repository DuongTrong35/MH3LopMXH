package com.example.MH3LopMxh.service;

import com.example.MH3LopMxh.model.Post;
import com.example.MH3LopMxh.model.PostUser;
import com.example.MH3LopMxh.model.User;
import com.example.MH3LopMxh.repository.PostRepository;
import com.example.MH3LopMxh.repository.PostUserRepository;
import com.example.MH3LopMxh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostUserRepository postUserRepository;
    
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    public Page<Post> getAllActivePosts(Pageable pageable) {
        return postRepository.findAllActivePosts(pageable);
    }
    
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
    
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserIdOrderByCreateAtDesc(userId);
    }
    
    @Transactional
    public Post createPost(Post post, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            post.setCreateAt(LocalDateTime.now());
            post.setUpdateAt(LocalDateTime.now());
            post.setActive(true);
            
            Post savedPost = postRepository.save(post);
            
            PostUser postUser = new PostUser();
            postUser.setPost(savedPost);
            postUser.setUser(user);
            
            postUserRepository.save(postUser);
            
            return savedPost;
        } else {
            throw new RuntimeException("Không tìm thấy người dùng với ID: " + userId);
        }
    }
    
    @Transactional
    public Post updatePost(Post post) {
        post.setUpdateAt(LocalDateTime.now());
        return postRepository.save(post);
    }
    
    @Transactional
    public void deletePost(Long id) {
        postRepository.findById(id).ifPresent(post -> {
            post.setActive(false);
            postRepository.save(post);
        });
    }
    
    public List<Post> searchPosts(String keyword) {
        return postRepository.searchPosts(keyword);
    }
    
    public void save(Post p) {
    	 postRepository.save(p);
    }
    public PostUser savepersonpost(long idpost,long iduse) {
        Optional<User> optionalUser = userRepository.findById(iduse);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            PostUser postUser = new PostUser();
            postUser.setIdPost(idpost);
            postUser.setUser(user);
//            postUser.setPost(post);
            return postUserRepository.save(postUser);
        }else {
            throw new RuntimeException("Không tìm thấy người dùng với ID: ");
        }
    }
    
//    public void save(Post p) {
//   	 postRepository.save(p);
//   }
//   public PostUser savepersonpost(long idpost,long iduse) {
//       Optional<User> optionalUser = userRepository.findById(iduse);
//       if (optionalUser.isPresent()) {
//           User user = optionalUser.get();
//           PostUser postUser = new PostUser();
//           postUser.setIdPost(idpost);
//           postUser.setUser(user);
////           postUser.setPost(post);
//           return postUserRepository.save(postUser);
//       }else {
//           throw new RuntimeException("Không tìm thấy người dùng với ID: ");
//       }
//   }
}
