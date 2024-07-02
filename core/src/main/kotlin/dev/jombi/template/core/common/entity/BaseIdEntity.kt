package dev.jombi.template.core.common.entity

import jakarta.persistence.*

@MappedSuperclass
abstract class BaseIdEntity(
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: FetchableId = FetchableId.NULL
)
