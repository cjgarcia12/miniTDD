package org.example.minitdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.repository")
public class MiniTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniTddApplication.class, args);
    }

}
