package com.windmillsmartsolutions.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages={"com.windmillsmartsolutions"})
@Controller
@CrossOrigin(origins="*")
public class StringoperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StringoperationsApplication.class, args);
	}

}
