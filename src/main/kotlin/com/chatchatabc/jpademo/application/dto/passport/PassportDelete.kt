package com.chatchatabc.jpademo.application.dto.passport

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.domain.model.User

data class PassportDeleteResponse(
    val user: User?,
    val error: ErrorContent?
)
