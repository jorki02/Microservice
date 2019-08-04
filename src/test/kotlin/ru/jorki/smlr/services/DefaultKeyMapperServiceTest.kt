package ru.jorki.smlr.services

import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class DefaultKeyMapperServiceTest {
    @InjectMocks
    val service: KeyMapperService = DefaultKeyMapperService()

    private val KEY: String = "aaaaa"
    private val LINK_A: String = "http://www.google.com"
    private val LINK_B: String = "http://wow.ru"
    private val ID_A: Long = 1000000L
    private val ID_B: Long = 1000001L
    private val KEY_A: String = "asd"
    private val KEY_B: String = "dsa"

    @Mock
    lateinit var converter:KeyConverterService

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(converter.idToKey(ID_A)).thenReturn(KEY_A)
        Mockito.`when`(converter.idToKey(ID_B)).thenReturn(KEY_B)
        Mockito.`when`(converter.keyToId(KEY_A)).thenReturn(ID_A)
        Mockito.`when`(converter.keyToId(KEY_B)).thenReturn(ID_B)
    }

    @Test
    fun clientCanAddLinks(){
        val keyA = service.add(LINK_A)
        assertEquals(KeyMapperService.Get.Link(LINK_A), service.getLink(keyA))
        val keyB = service.add(LINK_B)
        assertEquals(KeyMapperService.Get.Link(LINK_B), service.getLink(keyB))
    }

    @Test
    fun clientCanNotTakeLinkIfKayIsNotFoundInService(){
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }

}