package com.example.MH3LopMxh.model;

import jakarta.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_post")
    private Post post;

    @OneToOne
    @JoinColumn(name = "id", unique = true)
    private Image image;

    public PostImage() {
    }
    public PostImage(Image image,Post p) {
		super();
		this.image = image;
		this.post=p;
	}

	// Getters and Setters
    
    public Post getPost() {
        return post;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPost(Post post) {
        this.post = post;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
