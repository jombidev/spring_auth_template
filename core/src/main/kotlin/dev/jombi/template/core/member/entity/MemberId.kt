package dev.jombi.template.core.member.entity

import dev.jombi.template.core.member.repository.MemberJpaRepository
import kotlin.jvm.optionals.getOrNull

@JvmInline
value class MemberId(val id: Long) {
    fun fetch(repository: MemberJpaRepository) = repository.findById(id).getOrNull()
}
