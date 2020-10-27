package com.huyngoduc.demoproject;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);
    }

}
