package ru.jorki.smlr.model

import javax.persistence.*

@Entity
@Table(name = "links")
data class Link(
        val text: String = "",
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0L
)