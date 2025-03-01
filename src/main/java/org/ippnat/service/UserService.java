package org.ippnat.service;

import org.ippnat.entity.User;
import org.ippnat.exception.UserNotFoundException;
import org.ippnat.repository.UserDao;
import org.springframework.stereotype.Service;

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
        userDao.create(username);
        System.out.println("Пользователь username=" + username + " успешно создан");
    }

    public void deleteUser(Long id) {
        System.out.println("Удаление пользователя по ID=" + id);
        if (userDao.get(id).isPresent()) {
            userDao.delete(id);
            System.out.println("Пользователь успешно удален");
        } else {
            System.out.println("Пользователь с ID=" + id + " не найден");
        }
    }

    public User getUserById(Long id) {
        System.out.println("Получение пользователя с ID=" + id);
        Optional<User> user = userDao.get(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь с ID=" + id + " не найден");
        }
        System.out.println("Найден пользователь: " + user.get());
        return user.get();
    }

    public List<User> getAllUsers() {
        System.out.println("Получение всех пользователей");
        List<User> user = userDao.getAll();
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователи не найдены");
        }
        System.out.println("Количество найденных пользователей " + user.size() + " :" + user);
        return user;
    }
}
