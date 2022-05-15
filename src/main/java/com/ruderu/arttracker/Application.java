package com.ruderu.arttracker;

import com.ruderu.arttracker.util.DateFormat;
import com.samskivert.mustache.Mustache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public static class CustomDateFormatterConfiguration {

        @Bean
        public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader mustacheTemplateLoader) {
            return Mustache.compiler()
                    .withLoader(mustacheTemplateLoader)
                    .withFormatter(customDateFormatter());
        }

        private Mustache.Formatter customDateFormatter() {
            return value -> {
                if (value instanceof Date) {
                    return DateFormat.HTMLshort.format((Date) value);
                }
                return String.valueOf(value);
            };
        }

    }
}
