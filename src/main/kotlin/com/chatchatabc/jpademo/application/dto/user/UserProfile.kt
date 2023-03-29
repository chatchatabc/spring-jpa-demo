package com.chatchatabc.jpademo.application.dto.user

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.domain.model.User

data class UserProfileResponse(
    val user: User?,
    val error: ErrorContent?
)
