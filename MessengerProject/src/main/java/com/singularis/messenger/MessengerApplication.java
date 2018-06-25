package com.singularis.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MessengerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MessengerApplication.class);
    }
}
