package ru.jorki.smlr.configs

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class JacksonConfiguration {

    @Bean
    open fun kotlinModule() = KotlinModule()

}