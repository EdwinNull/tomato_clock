package com.weblearning.tomato.service.serviceImpl;

import com.weblearning.tomato.mapper.LoginMapper;
import com.weblearning.tomato.pojo.User;
import com.weblearning.tomato.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public void addUser(User user) {
        loginMapper.addUser(user);
    }

    @Override
    public User login(User user) {
        return loginMapper.login(user);
    }

    @Override
    public User getUserById(Integer id) {
        return loginMapper.getUserById(id);
    }
}
