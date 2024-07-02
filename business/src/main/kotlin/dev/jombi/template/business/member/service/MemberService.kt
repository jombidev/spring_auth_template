package dev.jombi.template.business.member.service

import dev.jombi.template.business.member.dto.MemberDto

interface MemberService {
    fun me(): MemberDto
}
