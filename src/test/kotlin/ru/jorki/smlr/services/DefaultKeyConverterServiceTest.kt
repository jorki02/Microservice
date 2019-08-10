package ru.jorki.smlr.services

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.math.abs

class DefaultKeyConverterServiceTest {

    val service: KeyConverterService = DefaultKeyConverterService()

    @Test
    fun GivenIdMustBeConvertedBothWays(){
        val rand = Random()
        for(i in 0..1000){
            val initId = abs(rand.nextLong())
            val key = service.idToKey(initId)
            val id = service.keyToId(key)
            assertEquals(id, initId)

        }
    }
}