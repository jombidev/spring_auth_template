package dev.jombi.template.core.member.entity

import dev.jombi.template.core.common.entity.BaseIdTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "tb_member")
data class Member(
    @Column(unique = true)
    val credential: String,

    @Column
    val password: String, // bcrypt

    @Column
    val name: String,
) : BaseIdTimeEntity()
