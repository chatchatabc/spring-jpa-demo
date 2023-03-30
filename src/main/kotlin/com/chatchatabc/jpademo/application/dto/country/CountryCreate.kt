package com.chatchatabc.jpademo.application.dto.country

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.domain.model.Country

data class CountryCreateRequest(
    val name: String,
)

data class CountryCreateResponse(
    val country: Country?,
    val error: ErrorContent?
)
