package com.chatchatabc.jpademojava.application.rest;

import com.chatchatabc.jpademojava.application.dto.ErrorContent;
import com.chatchatabc.jpademojava.application.dto.post.PostCreateRequest;
import com.chatchatabc.jpademojava.application.dto.post.PostResponse;
import com.chatchatabc.jpademojava.application.dto.post.PostUpdateRequest;
import com.chatchatabc.jpademojava.domain.model.Post;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.PostRepository;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/get")
    public ResponseEntity<Page<Post>> getPosts(
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            @RequestParam(required = false) String userId,
            Pageable pageable
    ) {
        try {
            // TODO: Move to a util service
            // Set default date range and override if dateFrom and dateTo are not null
            Instant now = Instant.now();
            // Start is the start of January 1, 1970
            LocalDate initialDate = LocalDate.of(1970, 1, 1);
            Instant start = initialDate.atStartOfDay(ZoneOffset.UTC).toInstant();
            // Make end the start of the next day
            Instant end = now.plus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS);
            if (dateFrom != null) {
                try {
                    start = LocalDate.parse(dateFrom, formatter).atStartOfDay().toInstant(ZoneOffset.UTC);
                } catch (Exception e) {
                    // do nothing
                }
            }
            if (dateTo != null) {
                try {
                    end = LocalDate.parse(dateTo, formatter).atStartOfDay().plus(1, ChronoUnit.DAYS).toInstant(ZoneOffset.UTC);
                } catch (Exception e) {
                    // do nothing
                }
            }
            // TODO: Can be improved SIGNIFICANTLY
            Optional<User> queriedUser = Optional.empty();
            if (userId != null) {
                queriedUser = userRepository.findById(userId);
            }
            Page<Post> posts;
            if (queriedUser.isPresent()) {
                posts = postRepository.findAllBetweenDateRangeByUser(start, end, queriedUser.get().getId(), pageable);
            } else {
                posts = postRepository.findAllBetweenDateRange(start, end, pageable);
            }
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create post
     *
     * @param request
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(
            @RequestBody PostCreateRequest request
    ) {
        try {
            // Get ID from Security Context
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Post post = modelMapper.map(request, Post.class);
            Post createdPost = postService.create(principal.getId(), post);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new PostResponse(createdPost, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new PostResponse(null, new ErrorContent("Create Post Error", e.getMessage()))
            );
        }
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable String postId,
            @RequestBody PostUpdateRequest request
    ) {
        try {
            Post post = modelMapper.map(request, Post.class);
            Post updatedPost = postService.update(postId, post);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new PostResponse(updatedPost, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new PostResponse(null, new ErrorContent("Update Post Error", e.getMessage()))
            );
        }
    }

    /**
     * Delete post
     *
     * @param postId
     * @return
     */
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(
            @PathVariable String postId
    ) {
        try {
            postService.delete(postId);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
