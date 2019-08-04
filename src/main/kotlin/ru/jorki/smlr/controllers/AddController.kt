package ru.jorki.smlr.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import ru.jorki.smlr.services.KeyMapperService
import javax.servlet.ServletResponse

@Controller
@RequestMapping("/add")
class AddController {

    @Autowired
    lateinit var service: KeyMapperService

    @RequestMapping(method = [RequestMethod.POST])
    @ResponseBody
    fun add(@RequestBody addRequest: AddRequest) =
        ResponseEntity.ok(AddResponse(addRequest.link, service.add(addRequest.link)))

    data class AddResponse (val link: String, val key:String)
    data class AddRequest (val link: String)

}
