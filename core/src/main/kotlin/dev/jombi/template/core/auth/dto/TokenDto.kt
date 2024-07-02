package dev.jombi.template.core.auth.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)
