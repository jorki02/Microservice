package ru.jorki.smlr.model.respositories

import org.springframework.data.repository.Repository
import ru.jorki.smlr.model.Link
import java.util.*

interface LinkRepository: Repository<Link, Long> {
    fun findById(id: Long?) : Optional<Link>
    fun save(link: Link) : Link
    fun findAll(): List<Link>
}