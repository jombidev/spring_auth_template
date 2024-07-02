package dev.jombi.template.infra.security.jwt

import org.springframework.security.authentication.AbstractAuthenticationToken

class JwtAuthToken(private val jwt: String) : AbstractAuthenticationToken(emptySet()) {
    override fun getCredentials(): Any {
        return jwt
    }

    override fun getPrincipal(): Any? {
        return null // jwt does not have principal
    }

    override fun setAuthenticated(authenticated: Boolean) {
        // no bypass
    }
}
