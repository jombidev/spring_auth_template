package dev.jombi.template.infra.security

import dev.jombi.template.infra.security.details.MemberDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class MemberContext {
    fun get() = (SecurityContextHolder.getContext().authentication.principal as MemberDetails).member
}
