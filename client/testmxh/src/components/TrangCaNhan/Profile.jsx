import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import React, { useState, useEffect } from 'react';
import './Profile.css';
import Navbar from '../impl/Navbar';
import cover from "../../assets/Images/Icons/testa.jpg";
import avatar from "../../assets/Images/Icons/testa.jpg";
import axios from 'axios';

const Post = ({ post, currentUserAvatar }) => (
  <div className="post-card">
    <div className="post-header">
      <img className="post-avatar" src={post.user?.profileImageUrl || avatar} alt={`${post.user?.firstName || 'User'} avatar`} />
      <div className="post-user-info">
        <strong>{post.user ? `${post.user.firstName} ${post.user.lastName}` : 'Unknown User'}</strong>
        <div className="post-meta">
          <span className="timestamp">{new Date(post.createAt).toLocaleDateString('vi-VN')}</span>
          <i className="fas fa-globe-americas privacy-icon"></i>
        </div>
      </div>
      <i className="fas fa-ellipsis-h post-options"></i>
    </div>

    <div className="post-content">
      {post.content && post.content.split('\n').map((line, i) => (
        <p key={i}>{line}</p>
      ))}
    </div>

    {post.imageUrl && (
      <div className="post-image">
        <img src={post.imageUrl || "/placeholder.svg"} alt="Post image" />
      </div>
    )}

    <div className="post-reactions">
      {post.likes > 0 && (
        <><i className="far fa-thumbs-up"></i> {post.likes} người đã bày tỏ cảm xúc</>
      )}
    </div>

    <div className="post-actions">
      <span><i className="far fa-thumbs-up"></i> Thích</span>
      <span><i className="far fa-comment"></i> Bình luận ({post.comments})</span>
      <span><i className="fas fa-share"></i> Chia sẻ ({post.shares})</span>
    </div>

    <div className="post-comment-box">
      <img className="comment-avatar" src={currentUserAvatar || "/placeholder.svg"} alt="Bạn avatar" />
      <input type="text" placeholder="Viết bình luận..." />
    </div>
  </div>
);

const Profile = () => {
  const [profileData, setProfileData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isVisibleavatar, setIsVisibleavatar] = useState(false);

  // ID người dùng - có thể lấy từ URL hoặc từ context/redux store
  const userId = 4638422354641785; // ID người dùng bạn đã cung cấp

  useEffect(() => {
    const fetchProfileData = async () => {
      try {
        setLoading(true);
        // Gọi API để lấy dữ liệu profile
        const response = await axios.get(`http://localhost:8080/api/profile/${userId}`);
        setProfileData(response.data);
        setLoading(false);
      } catch (err) {
        console.error('Error fetching profile data:', err);
        setError('Không thể tải dữ liệu. Vui lòng thử lại sau.');
        setLoading(false);
      }
    };

    fetchProfileData();
  }, [userId]);

  const hienkhunganh = () => {
    setIsVisibleavatar(!isVisibleavatar);
  };

  // Fallback data khi API chưa trả về kết quả
  const defaultCoverSrc = cover;
  const defaultAvatarSrc = avatar;

  if (loading) {
    return (
      <div className="app-root">
        <Navbar />
        <div className="loading-container">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Đang tải...</span>
          </div>
          <p>Đang tải thông tin cá nhân...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="app-root">
        <Navbar />
        <div className="error-container">
          <div className="alert alert-danger" role="alert">
            {error}
          </div>
          <button className="btn btn-primary" onClick={() => window.location.reload()}>
            Thử lại
          </button>
        </div>
      </div>
    );
  }

  // Sử dụng dữ liệu từ API hoặc dữ liệu mặc định nếu chưa có
  const userInfo = profileData?.userInfo || {};
  const posts = profileData?.posts || [];
  const friends = profileData?.friends || [];
  const photos = profileData?.photos || [];
  const aboutInfo = profileData?.aboutInfo || [];

  return (
    <>
      <div className="app-root">
        <Navbar />
        <div className="profile-bgTop">
          <div className="profile-containerTop">
            <div className="profile-header">
              <div
                className="cover-photo"
                style={{ backgroundImage: `url(${userInfo.coverImageUrl || defaultCoverSrc})` }}
                aria-label="Ảnh bìa cá nhân"
              />
              <button className="edit-cover-btn">Chỉnh sửa ảnh bìa</button>
              <img 
                className="avatar" 
                src={userInfo.profileImageUrl || defaultAvatarSrc} 
                alt="Avatar cá nhân" 
                onClick={hienkhunganh}
              />
            </div>
            <div className="profile-info">
              <h1>{userInfo.firstName} {userInfo.lastName}</h1>
              <p>{profileData?.totalFriends || 0} người bạn</p>
              <div>
                <h4>Giới thiệu</h4>
                <p><strong>Tiểu sử: </strong>{userInfo.bio || 'Chưa cập nhật trạng thái'}</p>
                <div className="info-details">
                  {aboutInfo
                    .filter(info => ['Giới tính', 'Sống tại'].includes(info.type))
                    .map((info, index) => (
                      <div key={index}>
                        <strong>{info.type}:</strong> {info.value}
                      </div>
                    ))}
                </div>
              </div>
          </div>

            <div className="actions-menubar">
              <a href="#" className="active">Bài viết</a>
              <a href="#">Bạn bè ({profileData?.totalFriends || 0})</a>
              <a href="#">Hình ảnh ({profileData?.totalPhotos || 0})</a>
              <a href="#">Chỉnh sửa cá nhân</a>
            </div>
          </div>
        </div>

        <div className="profile-container">
          <div className="create-profile-posts">
            <div className="new-posts">
              <span className="profile"></span>
              <input type="text" placeholder="Bạn đang nghĩ gì?" />
            </div>
          </div>

          <div className="profile-posts">
            {posts.length > 0 ? (
              posts.map(post => (
                <Post 
                  key={post.id} 
                  post={post} 
                  currentUserAvatar={userInfo.profileImageUrl || defaultAvatarSrc} 
                />
              ))
            ) : (
              <div className="no-posts-message">
                <p>Chưa có bài viết nào.</p>
              </div>
            )}
          </div>
        </div>
      </div>

      {isVisibleavatar && (
        <div className="avatar-modal" onClick={() => setIsVisibleavatar(false)}>
          <div className="avatar-modal-content" onClick={e => e.stopPropagation()}>
            <img src={userInfo.profileImageUrl || defaultAvatarSrc} alt="Avatar cá nhân" />
            <div className="avatar-modal-actions">
              <button className="btn btn-primary">Cập nhật ảnh đại diện</button>
              <button className="btn btn-secondary" onClick={() => setIsVisibleavatar(false)}>Đóng</button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default Profile;