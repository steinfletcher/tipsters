package io.tipsters

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@SpringBootApplication
open class Application {
    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder =
            Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule(), JavaTimeModule())

    @Bean
    open fun objectMapper(jackson2ObjectMapperBuilder: Jackson2ObjectMapperBuilder): ObjectMapper {
        val mapper = jackson2ObjectMapperBuilder.build<ObjectMapper>()
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        return mapper
    }

    @Bean
    open fun objectWriter(objectMapper: ObjectMapper): ObjectWriter = objectMapper.writer()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
