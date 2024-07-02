package dev.jombi.template.core.member.service

import dev.jombi.template.core.member.dto.MemberDto

interface MemberService {
    fun me(): MemberDto
}
