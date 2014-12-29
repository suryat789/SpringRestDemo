package dev.surya.spring.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("dev.surya.spring.rest") 
@EnableWebMvc
public class AppConfig {

}