// Post.java
package com.example.MH3LopMxh.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id_post")
    private Long idPost;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "post")
    private PostUser postUser;

    @OneToMany(mappedBy = "post")
    private Set<PostComment> comments = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<Interaction> interactions = new HashSet<>();

//    @OneToOne(cascade = CascadeType.PERSIST) // <-- quan trọng!
//    @OneToOne(mappedBy = "post")
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private PostImage postImage;

    
    public Post() {
    }
    public Post(Long idPost, String content, LocalDateTime createAt, LocalDateTime updateAt, boolean isActive) {
		super();
		this.idPost = idPost;
		this.content = content;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.isActive = isActive;
	}
    public Post(Long idPost, String content, LocalDateTime createAt, LocalDateTime updateAt, boolean isActive,PostImage pi) {
		super();
		this.idPost = idPost;
		this.content = content;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.isActive = isActive;
		this.postImage=pi;
	}
	// Getters and Setters
    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public PostUser getPostUser() {
        return postUser;
    }

    public void setPostUser(PostUser postUser) {
        this.postUser = postUser;
    }

    public Set<PostComment> getComments() {
        return comments;
    }

    public void setComments(Set<PostComment> comments) {
        this.comments = comments;
    }

    public Set<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(Set<Interaction> interactions) {
        this.interactions = interactions;
    }

    public PostImage getPostImage() {
        return postImage;
    }

    public void setPostImage(PostImage postImage) {
        this.postImage = postImage;
    }
}