package com.windmillsmartsolutions.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
* <h1>Social Sign-In Example Using Spring Boot application</h1>
* The program is the entry point for the Spring boot application.
*
* @author  Ravi Jagtap
* @version 1.0
*/
@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.windmillsmartsolutions"})
@ComponentScan(basePackages={"com.windmillsmartsolutions"})
@EntityScan({"com.windmillsmartsolutions.*"})
@EnableScheduling
@Controller
@EnableConfigurationProperties
@EnableAsync
@CrossOrigin(origins="*")
@EnableCaching
public class Application { 

   /**
   * This method main method and is the entry point for the spring boot application
   */
    public static void main(String[] args) {
        //System.out.println((new BCryptPasswordEncoder()).encode("password"));
    	SpringApplication.run(Application.class, args);
    }    
}
