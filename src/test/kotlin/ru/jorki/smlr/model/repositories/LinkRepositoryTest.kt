package ru.jorki.smlr.model.repositories

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.jorki.smlr.model.AbstractRepositoryTest
import ru.jorki.smlr.model.Link
import ru.jorki.smlr.model.respositories.LinkRepository
import java.util.*

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = [LinkRepositoryTest.DATASET])
open class LinkRepositoryTest: AbstractRepositoryTest() {

    @Autowired
    lateinit var repository: LinkRepository

    @Test
    fun findOneExisting(){
        val got: Optional<Link> = repository.findById(LINK_1_ID)
        assertThat(got.isPresent, equalTo(true))
        val link = got.get()
        assertThat(link, equalTo(Link(LINK_1_TEXT, LINK_1_ID)))
    }

    @Test
    fun findOneNotExisting(){
        val got: Optional<Link> = repository.findById(LINK_NOT_FOUND)
        assertThat(got.isPresent, equalTo(false))
    }

    @Test
    fun saveNew(){
        val toBeSaved = Link(LINK_TBS_TEXT)
        val got = repository.save(toBeSaved)
        val list = repository.findAll()

        assertThat(list, hasSize(4))
        assertThat(got.text, equalTo(LINK_TBS_TEXT))
    }

    companion object{
        const val DATASET = "classpath:datasets/link-table.xml"

        private val LINK_1_ID = 100500L
        private val LINK_1_TEXT = "http://www.google.com"
        private val LINK_TBS_TEXT = "http://www.ru"
        private val LINK_NOT_FOUND = 1L
    }

}