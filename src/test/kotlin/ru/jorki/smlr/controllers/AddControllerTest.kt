package ru.jorki.smlr.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.mock.http.server.reactive.MockServerHttpRequest.post
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.jorki.smlr.SmlrApplication
import ru.jorki.smlr.services.KeyMapperService

@RunWith(SpringRunner::class)
@WebAppConfiguration
@ContextConfiguration(classes = [SmlrApplication::class])
@TestPropertySource(locations = ["classpath:database-test.properties"])
class AddControllerTest {

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: AddController

    private val KEY = "key"
    private val LINK = "link"

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
        Mockito.`when`(service.add(LINK)).thenReturn(KEY)
    }

    @Test
    fun whenUserAddLinkHeTakesAKey(){
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(AddController.AddRequest(LINK))))
                .andExpect(jsonPath("$.key", equalTo(KEY)))
                .andExpect(jsonPath("$.link", equalTo(LINK)))
    }
}