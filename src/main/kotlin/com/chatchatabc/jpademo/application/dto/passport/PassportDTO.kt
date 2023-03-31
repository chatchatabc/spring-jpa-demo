package com.chatchatabc.jpademo.application.dto.passport

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.domain.model.Passport
import com.chatchatabc.jpademo.domain.model.User

data class PassportCreateRequest(
    val number: String,
)

data class PassportUpdateRequest(
    val number: String?,
)

data class PassportResponse(
    val user: User?,
    val error: ErrorContent?
)

data class PassportUpdateResponse(
    val passport: Passport?,
    val error: ErrorContent?
)

data class PassportDeleteResponse(
    val user: User?,
    val error: ErrorContent?
)