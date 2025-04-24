package com.socialnetwork.ejb.interfaces;

import com.socialnetwork.model.Post;
import java.util.List;

public interface PostService {
    Post createPost(Post post);
    Post updatePost(Long postId, Post updatedPost);
    void deletePost(Long postId);
    Post getPostById(Long postId);
    List<Post> getAllPosts();
    List<Post> getPostsByUser(Long userId);
    List<Post> getFeedForUser(Long userId); // Includes user + friends' posts
}
