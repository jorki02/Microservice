package ru.jorki.smlr

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@WebAppConfiguration
@ContextConfiguration(classes = [SmlrApplication::class])
class RedirectControllerTest {

    @Autowired
    lateinit var webApplicationContext:WebApplicationContext

    lateinit var mockMvc:MockMvc

    @Before
    fun init(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    val LINK = "/aaaaa"
    val REDIRECT_LINK = "http://www.google.com"
    val REDIRECT_STATUS = 302

    @Test
    fun testRedirectIfRightLink(){
        mockMvc.perform(MockMvcRequestBuilders.get(LINK))
                .andExpect(MockMvcResultMatchers.status().`is`(REDIRECT_STATUS))
                .andExpect(MockMvcResultMatchers.redirectedUrl(REDIRECT_LINK))

    }

    val BAD_LINK = "/aasdaw"
    val BAD_STATUS = 404

    @Test
    fun testStatus404IfBadLink(){
        mockMvc.perform(MockMvcRequestBuilders.get(BAD_LINK))
                .andExpect(MockMvcResultMatchers.status().`is`(BAD_STATUS))

    }

}