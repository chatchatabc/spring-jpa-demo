package com.chatchatabc.jpademo.domain.service

import com.chatchatabc.jpademo.domain.model.Post
import org.springframework.stereotype.Service

@Service
interface PostService {

    /**
     * Create Post
     */
    fun create(userId:String, post: Post): Post

    /**
     * Update Post
     */
    fun update(postId: String, newPostInfo: Post): Post

    /**
     * Delete Post
     */
    fun delete(postId: String)
}