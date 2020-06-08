package com.meng.sleeve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;

@SpringBootApplication
@EnableCaching
public class SleeveApplication {
    public static void main(String[] args) {
        SpringApplication.run(SleeveApplication.class, args);
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringDataJacksonConfiguration.class);
    }
}
