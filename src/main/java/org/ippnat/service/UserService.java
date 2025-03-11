package org.ippnat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ippnat.entity.User;
import org.ippnat.model.exception.UserNotFoundException;
import org.ippnat.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements CommandLineRunner {

    private final UserRepository userRepository;
    private static final List<String> names = Arrays.asList(
            "Adam", "Eva", "Petr", "Alice", "Max", "Anna", "Kate", "Sophia", "Pavel", "Natalie"
    );
    private static final Random random = new Random();

    @Override
    public void run(String... args) {
        for (int i = 0; i < 10; i++) {
            createUser(names.get(i));
        }
        List<Long> userIds = getAllUsers()
                .stream()
                .map(User::getId)
                .toList();
        Long randomUserId = userIds.get(random.nextInt(userIds.size()));
        log.info("Найден пользователь: {}", getUserById(randomUserId));
        deleteUser(randomUserId);
    }

    public User createUser(String username) {
        log.info("Создание пользователя с username={}", username);
        try {
            return userRepository.findByUsername(username)
                    .map(user -> {
                        log.info("Пользователь с таким именем уже существует: {}", user);
                        return user;
                    })
                    .orElseGet(() -> {
                        User user = new User(username);
                        user = userRepository.save(user);
                        log.info("Пользователь username={} успешно создан", username);
                        return user;
                    });
        } catch (Exception e) {
            log.error("Ошибка при создании пользователя username={}", username, e);
            throw new RuntimeException("Ошибка при создании пользователя", e);
        }
    }

    public void deleteUser(Long id) {
        log.info("Удаление пользователя по ID={}", id);
        userRepository.deleteById(id);
        log.info("Пользователь успешно удален");
    }

    public User getUserById(Long id) {
        log.info("Получение пользователя с ID={}", id);
        return userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("Пользователь с ID=" + id + " не найден"));
    }

    public List<User> getAllUsers() {
        log.info("Получение всех пользователей");
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.error("Пользователи не найдены");
            return users;
        }
        log.info("Количество найденных пользователей {} :{}", users.size(), users);
        return users;
    }

}
