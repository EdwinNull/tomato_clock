package com.weblearning.tomato.service;

import com.weblearning.tomato.pojo.User;
public interface LoginService {
    void addUser(User user);
    User login(User user);
    User getUserById(Integer id);
}
