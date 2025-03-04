package org.ippnat;

import org.ippnat.exception.UserNotFoundException;
import org.ippnat.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.ippnat.entity.User;

@ComponentScan
public class Application {

    private static final List<String> names = Arrays.asList(
            "Adam", "Eva", "Petr", "Alice", "Max", "Anna", "Kate", "Sophia", "Pavel", "Natalie"
    );
    private static final Random random = new Random();

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);

        UserService userService = context.getBean(UserService.class);

        for (int i = 0; i < 10; i++) {
            userService.createUser(names.get(i));
        }

        try {
            List<Long> userIds = userService.getAllUsers().stream()
                    .map(User::getId)
                    .toList();
            Long randomUserId = userIds.get(random.nextInt(userIds.size()));
            System.out.println("Найден пользователь: " + userService.getUserById(randomUserId));
            userService.deleteUser(randomUserId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            context.close();
        }
    }
}