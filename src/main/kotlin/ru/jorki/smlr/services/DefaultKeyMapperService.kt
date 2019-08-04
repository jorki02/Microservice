package ru.jorki.smlr.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.jorki.smlr.model.Link
import ru.jorki.smlr.model.respositories.LinkRepository

@Component
class DefaultKeyMapperService : KeyMapperService {
    @Autowired
    lateinit var converter:KeyConverterService

    @Autowired
    lateinit var linkRepo: LinkRepository

    override fun add(link: String) =
        converter.idToKey(linkRepo.save(Link(link)).id)

    override fun getLink(key: String): KeyMapperService.Get {
        val id = converter.keyToId(key)
        val result = linkRepo.findById(id)

        return if(result.isPresent){
            KeyMapperService.Get.Link(result.get().text)
        } else {
            KeyMapperService.Get.NotFound(key)
        }
    }
}

