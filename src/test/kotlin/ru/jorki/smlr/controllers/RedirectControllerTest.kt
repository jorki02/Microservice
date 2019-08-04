package ru.jorki.smlr.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.jorki.smlr.SmlrApplication
import ru.jorki.smlr.services.KeyMapperService

@RunWith(SpringRunner::class)
@WebAppConfiguration
@ContextConfiguration(classes = [SmlrApplication::class])
@TestPropertySource(locations = ["classpath:database-test.properties"])
class RedirectControllerTest {

    @Autowired
    lateinit var webApplicationContext:WebApplicationContext

    lateinit var mockMvc:MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()

        Mockito.`when`(service.getLink(LINK)).thenReturn(KeyMapperService.Get.Link(REDIRECT_LINK))
        Mockito.`when`(service.getLink(BAD_LINK)).thenReturn(KeyMapperService.Get.NotFound(BAD_LINK))
    }

    val LINK = "aaaaa"
    val REDIRECT_LINK = "http://www.google.com"
    val REDIRECT_STATUS = 302

    @Test
    fun testRedirectIfRightLink(){
        mockMvc.perform(MockMvcRequestBuilders.get("/$LINK"))
                .andExpect(MockMvcResultMatchers.status().`is`(REDIRECT_STATUS))
                .andExpect(MockMvcResultMatchers.redirectedUrl(REDIRECT_LINK))
    }

    val BAD_LINK = "aasdaw"
    val BAD_STATUS = 404

    @Test
    fun testStatus404IfBadLink(){
        mockMvc.perform(MockMvcRequestBuilders.get("/$BAD_LINK"))
                .andExpect(MockMvcResultMatchers.status().`is`(BAD_STATUS))
    }

}