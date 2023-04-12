package com.chatchatabc.jpademo.impl.domain.service

import com.chatchatabc.jpademo.domain.model.Post
import com.chatchatabc.jpademo.domain.repository.PostRepository
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.PostService
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : PostService {

    /**
     * Create Post
     */
    override fun create(userId: String, post: Post): Post {
        val user = userRepository.findById(userId)
        if (user.isEmpty) {
            throw Exception("User not found")
        }
        post.user = user.get()
        return postRepository.save(post)
    }

    /**
     * Update Post
     */
    override fun update(postId: String, newPostInfo: Post): Post {
        val post = postRepository.findById(postId)
        if (post.isEmpty) {
            throw Exception("Post not found")
        }
        if (newPostInfo.title != null) {
            post.get().title = newPostInfo.title
        }
        if (newPostInfo.content != null) {
            post.get().content = newPostInfo.content
        }
        return postRepository.save(post.get())
    }

    /**
     * Delete Post
     */
    override fun delete(postId: String) {
        // Not really needed
        val post = postRepository.findById(postId)
        if (post.isEmpty) {
            throw Exception("Post not found")
        }
        postRepository.delete(post.get())
    }
}