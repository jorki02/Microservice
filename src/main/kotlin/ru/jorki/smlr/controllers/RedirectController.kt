package ru.jorki.smlr.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/{value}")
class RedirectController {

    @RequestMapping
    fun redirect(@PathVariable("value") value: String, response: HttpServletResponse){
        if(value == "aaaaa") {
            response.setHeader("Location", "http://www.google.com")
            response.status = 302
        } else {
            response.status = 404
        }
    }
}