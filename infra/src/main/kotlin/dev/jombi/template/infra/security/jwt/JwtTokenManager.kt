package dev.jombi.template.infra.security.jwt

import dev.jombi.template.common.exception.CustomException
import dev.jombi.template.common.exception.GlobalExceptionDetail
import dev.jombi.template.core.auth.exception.AuthExceptionDetails
import dev.jombi.template.core.auth.extern.TokenGenerator
import dev.jombi.template.core.member.repository.MemberJpaRepository
import dev.jombi.template.core.member.MemberHolder
import dev.jombi.template.core.member.details.MemberDetails
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenManager(
    private val jwtProperties: JwtProperties,
    private val memberContext: MemberHolder,
    private val memberJpaRepository: MemberJpaRepository
) : TokenGenerator, TokenValidator {
    override fun generateAccessToken(): String {
        return generateToken(JwtType.ACCESS_TOKEN)
    }

    override fun generateRefreshToken(): String {
        return generateToken(JwtType.REFRESH_TOKEN)
    }

    fun generateToken(type: JwtType): String {
        val date = Date()
        return Jwts.builder()
            .header()
            .type(type.name)
            .and()
            .signWith(jwtProperties.secretKey(), Jwts.SIG.HS512)
            .issuer(jwtProperties.issuer)
            .subject(memberContext.get().credential)
            .issuedAt(date)
            .expiration(date.apply { time += expireDuration(type) })
            .compact()
    }

    fun expireDuration(type: JwtType): Long {
        return when (type) {
            JwtType.ACCESS_TOKEN -> jwtProperties.accessExpiresAfter
            JwtType.REFRESH_TOKEN -> jwtProperties.refreshExpiresAfter
        }
    }

    override fun refreshToken(refreshToken: String): String {
        TODO()
    }

    private val jwtParser = Jwts.parser()
        .verifyWith(jwtProperties.secretKey())
        .requireIssuer(jwtProperties.issuer)
        .build()


    override fun validate(jwt: String): Authentication {
        try {
            val parsed = jwtParser.parse(jwt)

            val pl = parsed.payload as Claims

            if (JwtType.valueOf(parsed.header.type) != JwtType.ACCESS_TOKEN)
                throw CustomException(AuthExceptionDetails.TOKEN_TYPE_MISMATCH)

            val member = memberJpaRepository.findMemberByCredential(pl.subject)
                ?: throw MalformedJwtException("subject")

            return UsernamePasswordAuthenticationToken(MemberDetails(member), null, setOf())
        } catch (e: ExpiredJwtException) {
            throw CustomException(AuthExceptionDetails.EXPIRED_TOKEN)
        } catch (e: MalformedJwtException) {
            throw CustomException(AuthExceptionDetails.INVALID_TOKEN)
        } catch (e: CustomException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)
        }
    }
}
