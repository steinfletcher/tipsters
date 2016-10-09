package io.tipsters.uiapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class Application {

  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder objectMapperBuilder) {
    ObjectMapper objectMapper = objectMapperBuilder.build();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .registerModule(new JavaTimeModule());
    return objectMapper;
  }

  public static void main(String...args) {
    SpringApplication.run(Application.class, args);
  }
}
