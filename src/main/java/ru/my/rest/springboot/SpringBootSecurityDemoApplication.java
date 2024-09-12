package ru.my.rest.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public  static final Logger log = LoggerFactory.getLogger(SpringBootSecurityDemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
        log.info("Приложение запущено. Перейдите по ссылке: http://localhost:8080/login");
    }

}
