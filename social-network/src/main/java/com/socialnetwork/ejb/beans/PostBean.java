package com.socialnetwork.ejb.beans;

import com.socialnetwork.ejb.interfaces.PostService;
import com.socialnetwork.model.Post;
import com.socialnetwork.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PostBean implements PostService {

    @PersistenceContext(unitName = "social-networkPU")
    private EntityManager em;

    @Override
    public Post createPost(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public Post updatePost(Long postId, Post updatedPost) {
        Post existing = em.find(Post.class, postId);
        if (existing != null) {
            existing.setContent(updatedPost.getContent());
            existing.setImageUrl(updatedPost.getImageUrl());
            em.merge(existing);
        }
        return existing;
    }

    @Override
    public void deletePost(Long postId) {
        Post post = em.find(Post.class, postId);
        if (post != null) {
            em.remove(post);
        }
    }

    @Override
    public Post getPostById(Long postId) {
        return em.find(Post.class, postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return em.createQuery("SELECT p FROM Post p ORDER BY p.createdAt DESC", Post.class).getResultList();
    }

    @Override
    public List<Post> getPostsByUser(Long userId) {
        return em.createQuery("SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.createdAt DESC", Post.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Post> getFeedForUser(Long userId) {
        // This could later include friend logic - simplified for now
        User user = em.find(User.class, userId);
        if (user == null) return List.of();

        List<Long> friendIds = user.getConnections().stream()
                .map(connection -> connection.getFriend().getId())
                .collect(Collectors.toList());
        friendIds.add(userId); // include user's own posts

        return em.createQuery("SELECT p FROM Post p WHERE p.user.id IN :ids ORDER BY p.createdAt DESC", Post.class)
                .setParameter("ids", friendIds)
                .getResultList();
    }
}
