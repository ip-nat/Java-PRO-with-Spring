package org.ippnat.service;

import org.ippnat.entity.User;

import java.util.List;

public interface UserService {

    User createUser(String username);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> getAllUsers();

}
