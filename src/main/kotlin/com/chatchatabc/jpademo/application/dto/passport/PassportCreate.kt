package com.chatchatabc.jpademo.application.dto.passport

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.domain.model.User

data class PassportCreateRequest(
    val number: String,
)

data class PassportCreateResponse(
    val user: User?,
    val error: ErrorContent?
)