package com.chatchatabc.jpademo.application.dto.country

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.domain.model.User

data class CountryAssignRequest(
    val userId: String,
    val countryId: String
)

data class CountryAssignResponse(
    val user: User?,
    val error: ErrorContent?
)

data class CountryUnassignRequest(
    val userId: String
)
