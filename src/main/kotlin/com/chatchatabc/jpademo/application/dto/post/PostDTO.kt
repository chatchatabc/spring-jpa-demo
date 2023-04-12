package com.chatchatabc.jpademo.application.dto.post

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.domain.model.Post

data class PostCreateRequest(
    val title: String,
    val content: String,
)

data class PostUpdateRequest(
    val title: String?,
    val content: String?,
)

data class PostResponse(
    val post: Post?,
    val error: ErrorContent?
)