package jp.mts.simpletaskboard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("jp.mts.simpletaskboard.web.controllers")
@EnableWebMvc
public class WebConfig {

}