package dev.jombi.template.core.member.service

import dev.jombi.template.core.member.MemberHolder
import dev.jombi.template.business.member.dto.MemberDto
import dev.jombi.template.business.member.service.MemberService
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberHolder: MemberHolder
) : MemberService {
    override fun me(): MemberDto {
        val member = memberHolder.get()
        return MemberDto(member.name)
    }
}
