package com.example.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static java.util.Optional.ofNullable;
import org.modelmapper.ModelMapper;

@SpringBootApplication
@EnableTransactionManagement
public class UserManagementApplication {

    public static void main(String[] args) {
        String environment = ofNullable(System.getenv("environment_name")).orElse("local");
        System.setProperty("spring.profiles.active", environment);
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
