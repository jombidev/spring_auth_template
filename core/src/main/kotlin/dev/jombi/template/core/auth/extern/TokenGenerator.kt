package dev.jombi.template.core.auth.extern

interface TokenGenerator {
    fun generateAccessToken(): String
    fun generateRefreshToken(): String
    fun refreshToken(refreshToken: String): String
}
