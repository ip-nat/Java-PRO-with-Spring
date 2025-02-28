package org.ippnat.service;

import org.ippnat.entity.User;
import org.ippnat.repository.UserDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(String username) {
        System.out.println("Создание пользователя с username=" + username);
        userDao.createUser(username);
    }

    public void deleteUser(Long id) {
        System.out.println("Удаление пользователя по ID=" + id);
        userDao.deleteUser(id);
        System.out.println("Пользователь успешно удален");
    }

    public User getUserById(Long id) {
        System.out.println("Получение пользователя с ID=" + id);
        Optional<User> user = userDao.getUserById(id);
        if (user.isEmpty()) {
            System.out.println("Пользователь с ID=" + id + " не найден");
            return null;
        }
        System.out.println("Найден пользователь: " + user.get());
        return user.get();
    }

    public List<User> getAllUsers() {
        System.out.println("Получение всех пользователей");
        List<User> user = userDao.getAllUsers();
        if (user.isEmpty()) {
            System.out.println("Пользователи не найдены");
            return new ArrayList<>();
        }
        System.out.println("Количество найденных пользователей " + user.size() + " :" + user);
        return user;
    }
}
