package ru.jorki.smlr.services

import org.junit.Test
import kotlin.test.assertEquals

class DefaultKeyMapperServiceTest {
    val service: KeyMapperService = DefaultKeyMapperService()

    private val KEY: String = "aaaaa"
    private val LINK: String = "http://www.google.com"
    private val NEW_LINK: String = "http://wow.ru"

    @Test
    fun clientCanAddNewKeyWithLink(){
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun clientCanNotAddExistingKey(){
        service.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY,NEW_LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun clientCanNotTakeLinkIfKayIsNotFoundInService(){
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }

}