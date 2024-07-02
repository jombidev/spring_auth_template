package dev.jombi.template.core.auth.service

import dev.jombi.template.business.auth.dto.TokenDto
import dev.jombi.template.core.auth.extern.TokenGenerator
import dev.jombi.template.business.auth.service.AuthService
import dev.jombi.template.common.exception.CustomException
import dev.jombi.template.core.auth.exception.AuthExceptionDetails
import dev.jombi.template.core.member.entity.Member
import dev.jombi.template.core.member.repository.MemberJpaRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val memberRepository: MemberJpaRepository,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val tokenGenerator: TokenGenerator,
) : AuthService {
    override fun authenticate(credential: String, password: String): TokenDto {
        val token = UsernamePasswordAuthenticationToken(credential, password)

        val auth = authenticationManager.authenticate(token)
        SecurityContextHolder.getContext().authentication = auth

        val access = tokenGenerator.generateAccessToken()
        val refresh = tokenGenerator.generateRefreshToken()

        return TokenDto(access, refresh)
    }

    override fun createNewMember(name: String, credential: String, password: String): Long {
        if (memberRepository.existsByCredential(credential))
            throw CustomException(AuthExceptionDetails.USER_ALREADY_EXISTS, credential)

        return memberRepository.save(Member(credential, passwordEncoder.encode(password), name))
            .id.id
    }

    override fun getNewToken(refreshToken: String): TokenDto {
        val newAccessToken = tokenGenerator.refreshToNewToken(refreshToken)
        return TokenDto(
            newAccessToken,
            refreshToken // no changes ;)
        )
    }
}
