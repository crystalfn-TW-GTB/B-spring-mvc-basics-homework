package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExitsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private Map<String, UserDto> userMap = new HashMap<>();

    public void register(UserDto userDto) {
        String userName = userDto.getName();

        if (userMap.containsKey(userName)) {
            throw new UserExitsException("用户已存在");
        }

        userMap.put(userName, userDto);
    }
}
