package com.chatchatabc.jpademo.application.dto.user

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.domain.model.User

data class UserRegisterRequest(
    val email: String,
    val username: String,
    val password: String,
)

data class UserRegisterResponse(
    val user: User?,
    val error: ErrorContent?
)