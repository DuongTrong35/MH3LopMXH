package com.example.MH3LopMxh.dto;

import java.time.LocalDateTime;

public class PostDTO {
    private Long id;
    private String content;
    private String imageUrl;
    private LocalDateTime createAt;
    private int likes;
    private int comments;
    private int shares;
    private UserProfileDTO user;
    private long iduser;
    public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	// Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public LocalDateTime getCreateAt() {
        return createAt;
    }
    
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
    
    public int getLikes() {
        return likes;
    }
    
    public void setLikes(int likes) {
        this.likes = likes;
    }
    
    public int getComments() {
        return comments;
    }
    
    public void setComments(int comments) {
        this.comments = comments;
    }
    
    public int getShares() {
        return shares;
    }
    
    public void setShares(int shares) {
        this.shares = shares;
    }
    
    public UserProfileDTO getUser() {
        return user;
    }
    
    public void setUser(UserProfileDTO user) {
        this.user = user;
    }
}
