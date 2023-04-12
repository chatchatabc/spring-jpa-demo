package com.chatchatabc.jpademojava.impl.domain.service;

import com.chatchatabc.jpademojava.domain.model.Post;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.PostRepository;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Create post
     *
     * @param userId
     * @param post
     * @return
     */
    @Override
    public Post create(String userId, Post post) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        post.setUser(user.get());
        return postRepository.save(post);
    }

    /**
     * Update posts
     *
     * @param postId
     * @param newPostInfo
     * @return
     */
    @Override
    public Post update(String postId, Post newPostInfo) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new Exception("Post not found");
        }

        // Apply updates
        if (newPostInfo.getTitle() != null) {
            post.get().setTitle(newPostInfo.getTitle());
        }
        if (newPostInfo.getContent() != null) {
            post.get().setContent(newPostInfo.getContent());
        }
        return postRepository.save(post.get());
    }

    /**
     * Delete post
     *
     * @param postId
     */
    @Override
    public void delete(String postId) throws Exception {
        // Not really needed
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new Exception("Post not found");
        }
        postRepository.delete(post.get());
    }
}
