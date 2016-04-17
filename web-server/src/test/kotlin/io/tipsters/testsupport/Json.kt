package io.tipsters.testsupport

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

class Json {
    companion object {
        val jsonMapper = Jackson2ObjectMapperBuilder()
                .modulesToInstall(KotlinModule(), JavaTimeModule()).build<ObjectMapper>()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}
