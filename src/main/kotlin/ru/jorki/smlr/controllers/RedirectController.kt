package ru.jorki.smlr.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.jorki.smlr.services.KeyMapperService
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/{value}")
class RedirectController {

    @Autowired
    lateinit var service: KeyMapperService

    @RequestMapping
    fun redirect(@PathVariable("value") value: String, response: HttpServletResponse){
        val result = service.getLink(value)

        when(result){
            is KeyMapperService.Get.Link -> {
                response.setHeader("Location", result.link)
                response.status = 302
            }
            is KeyMapperService.Get.NotFound -> {
                response.status = 404
            }
        }
    }
}