package dev.jombi.template.core.common.entity

import jakarta.persistence.Column
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

abstract class BaseIdTimeEntity(
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private val _createdAt: LocalDateTime? = null,
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private val _updatedAt: LocalDateTime? = null
) : BaseIdEntity() {
    val createdAt get() = _createdAt
    val updatedAt get() = _updatedAt
}
