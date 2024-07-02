package dev.jombi.template.core.auth.service

import dev.jombi.template.core.auth.dto.TokenDto

interface AuthService {
    fun authenticate(credential: String, password: String): TokenDto
    fun createNewMember(name: String, credential: String, password: String): Long
    fun getNewToken(refreshToken: String): TokenDto
}
