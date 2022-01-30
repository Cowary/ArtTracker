package com.ruderu.mediarecord;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Formatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        private Formatter customDateFormatter() {
            return new Formatter() {
                public String format(Object value) {
                    if (value instanceof Date) {
                        return dateFormat.format((Date) value);
                    }
                    return String.valueOf(value);
                }
                protected final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            };
        }

    }


}
