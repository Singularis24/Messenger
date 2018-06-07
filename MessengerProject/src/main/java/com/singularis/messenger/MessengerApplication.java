package com.singularis.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MessengerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MessengerApplication.class);
        //UserService userService = ctx.getBean(UserService.class);
        //User user = userService.addUser(new User("vasya8", "123", "8425", "vasya", "bro", "admin"));
        //User user = userService.getByLogin("vasya");
        //System.out.println(user.toString());
    }
}
