package com.chatchatabc.jpademojava.domain.service;

import com.chatchatabc.jpademojava.domain.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    /**
     * Create post
     *
     * @param userId
     * @param post
     * @return
     */
    Post create(String userId, Post post) throws Exception;

    /**
     * Update posts
     *
     * @param postId
     * @param newPostInfo
     * @return
     */
    Post update(String postId, Post newPostInfo) throws Exception;

    /**
     * Delete post
     *
     * @param postId
     */
    void delete(String postId) throws Exception;
}
