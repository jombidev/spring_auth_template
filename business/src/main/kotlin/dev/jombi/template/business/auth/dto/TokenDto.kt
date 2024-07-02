package dev.jombi.template.business.auth.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)
