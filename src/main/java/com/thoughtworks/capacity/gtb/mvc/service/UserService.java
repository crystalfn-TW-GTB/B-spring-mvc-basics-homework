package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.exception.LoginMessageErrorException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExitsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

@Service
public class UserService {
    private Map<String, UserDto> userMap = new HashMap<>();
    private AtomicInteger userId = new AtomicInteger(1);

    public void register(UserDto userDto) {
        String userName = userDto.getUsername();

        if (userMap.containsKey(userName)) {
            throw new UserExitsException("用户已存在");
        }

        userDto.setId(userId.getAndIncrement());
        userMap.put(userName, userDto);
    }

    public UserDto login(String username, String password) {
        if (!userMap.containsKey(username)) {
            throw new LoginMessageErrorException("用户名或密码错误");
        }

        UserDto user = userMap.get(username);
        if (!user.getPassword().equals(password)) {
            throw new LoginMessageErrorException("用户名或密码错误");
        }

        return user;
    }
}
