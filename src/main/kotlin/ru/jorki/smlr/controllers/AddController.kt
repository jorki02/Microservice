package ru.jorki.smlr.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import ru.jorki.smlr.services.KeyMapperService
import javax.servlet.ServletResponse

@Controller
class AddController {

    @Value("\${smlr.prefix}")
    private lateinit var prefix: String

    @Autowired
    lateinit var service: KeyMapperService

    @RequestMapping(path = ["add"], method = [RequestMethod.POST])
    @ResponseBody
    fun addRest(@RequestBody addRequest: AddRequest) =
        ResponseEntity.ok(AddResponse(addRequest.link, service.add(addRequest.link)))

    @RequestMapping(path = ["addHtml"], method = [RequestMethod.POST])
    fun addHtml(model: Model, @RequestParam(value = "link", required = true) link: String): String{
        val result = add(link)
        model.addAttribute("link", result.link)
        model.addAttribute("keyed", prefix + result.key)

        return "result"
    }

    data class AddResponse (val link: String, val key:String)
    data class AddRequest (val link: String)

    private fun add(link: String) = AddResponse(link, service.add(link))

}
