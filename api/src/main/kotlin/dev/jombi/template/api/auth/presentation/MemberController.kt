package dev.jombi.template.api.auth.presentation

import dev.jombi.template.api.auth.dto.response.MemberInfoResponseDto
import dev.jombi.template.common.response.ResponseData
import dev.jombi.template.core.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/me")
    fun me(): ResponseEntity<ResponseData<MemberInfoResponseDto>> {
        val me = memberService.me()

        return ResponseData.ok(data = MemberInfoResponseDto(me.name))
    }
}
