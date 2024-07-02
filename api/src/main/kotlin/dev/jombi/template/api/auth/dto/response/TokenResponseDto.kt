package dev.jombi.template.api.auth.dto.response

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)
