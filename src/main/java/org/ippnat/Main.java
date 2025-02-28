package org.ippnat;

import org.ippnat.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        UserService userService = context.getBean(UserService.class);

        userService.createUser("Adam");
        userService.createUser("Eva");
        userService.getAllUsers();
        userService.getUserById(1L);
        userService.deleteUser(1L);

        context.close();
    }
}