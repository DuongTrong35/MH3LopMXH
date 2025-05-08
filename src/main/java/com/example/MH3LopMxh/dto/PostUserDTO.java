package com.example.MH3LopMxh.dto;

import com.example.MH3LopMxh.model.PostUser;

public class PostUserDTO {
	 private long idPost;
	    private Long userId;
	    private String userName;

	    public PostUserDTO(PostUser pu) {
	        this.idPost = pu.getIdPost();
	        this.userId = pu.getUser().getIdUser();
	        this.userName = pu.getUser().getLastName(); // hoặc tùy bạn: firstName, full name
	    }

		public long getIdPost() {
			return idPost;
		}

		public void setIdPost(long idPost) {
			this.idPost = idPost;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
	    
}
