package com.chatchatabc.jpademo.application.rest

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.post.PostCreateRequest
import com.chatchatabc.jpademo.application.dto.post.PostResponse
import com.chatchatabc.jpademo.application.dto.post.PostUpdateRequest
import com.chatchatabc.jpademo.domain.model.Post
import com.chatchatabc.jpademo.domain.model.User
import com.chatchatabc.jpademo.domain.repository.PostRepository
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.PostService
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

@RestController
@RequestMapping("/api/post")
class PostController(
    private val postService: PostService,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val modelMapper = ModelMapper()

    /**
     * Get Posts
     */
    @GetMapping("/get")
    fun getPosts(
        @RequestParam(required = false) dateFrom: String?,
        @RequestParam(required = false) dateTo: String?,
        @RequestParam(required = false) userId: String?,
        pageable: Pageable
    ): ResponseEntity<Page<Post>> {
        return try {
            // TODO: Move to a util service
            // Set default date range and override if dateFrom and dateTo are not null
            val now = Instant.now()
            // start is the start of january 1, 1970
            val initialDate = LocalDate.of(1970, 1, 1)
            var start = initialDate.atStartOfDay(ZoneOffset.UTC).toInstant()
            // make end the start of the next day at 00:00:00
            var end = now.plus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS)
            if (dateFrom != null) {
                try {
                    start = LocalDate.parse(dateFrom, formatter).atStartOfDay().toInstant(ZoneOffset.UTC)
                } catch (e: Exception) {
                    // do nothing
                }
            }
            if (dateTo != null) {
                try {
                    // make end the start of the next day at 00:00:00
                    end = LocalDate.parse(dateTo, formatter).atStartOfDay().plus(1, ChronoUnit.DAYS)
                        .toInstant(ZoneOffset.UTC)
                } catch (e: Exception) {
                    // do nothing
                }
            }
            // TODO: Can be improved SIGNIFICANTLY
            var queriedUser: Optional<User>? = null
            if (userId != null) {
                queriedUser = userRepository.findById(userId)
            }
            val posts: Page<Post> = if (queriedUser?.isPresent == true) {
                postRepository.findAllBetweenDateRangeByUser(start, end, queriedUser.get(), pageable)
            } else {
                postRepository.findAllBetweenDateRange(start, end, pageable)
            }

            ResponseEntity.ok(posts)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.badRequest().build()
        }
    }

    /**
     * Create Post
     */
    @PostMapping("/create")
    fun createPost(
        @RequestBody request: PostCreateRequest
    ): ResponseEntity<PostResponse> {
        return try {
            // Get ID from Security Context
            val principal = SecurityContextHolder.getContext().authentication.principal as User
            val post = modelMapper.map(request, Post::class.java)
            val createdPost = postService.create(principal.id, post)
            ResponseEntity.status(HttpStatus.CREATED).body(PostResponse(createdPost, null))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                PostResponse(null, ErrorContent("Create Post Error", e.message))
            )
        }
    }

    /**
     * Update Posts
     */
    @PutMapping("/update/{postId}")
    fun updatePost(
        @PathVariable postId: String,
        @RequestBody request: PostUpdateRequest
    ): ResponseEntity<PostResponse> {
        return try {
            val post = modelMapper.map(request, Post::class.java)
            val updatedPost = postService.update(postId, post)
            ResponseEntity.ok(PostResponse(updatedPost, null))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                PostResponse(null, ErrorContent("Update Post Error", e.message))
            )
        }
    }

    /**
     * Delete Posts
     */
    @DeleteMapping("/delete/{postId}")
    fun deletePost(
        @PathVariable postId: String
    ): ResponseEntity<String> {
        return try {
            postService.delete(postId)
            ResponseEntity.ok(null)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }
}