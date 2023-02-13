package application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"controller", "model", "service", "utils"})
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"model"})
@Slf4j
public class KameleoonApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KameleoonApplication.class);
        Map<String, Object> customConfig = new HashMap<>();
        customConfig.put("server.port", "3030");
        app.setDefaultProperties(customConfig);
        app.run(args);
    }
}