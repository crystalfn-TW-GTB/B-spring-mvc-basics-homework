package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@Validated
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid UserDto userDto) {
        userService.register(userDto);
    }

    @GetMapping("/login")
    public UserDto login(@RequestParam(name = "username")
                         @NotEmpty(message = "用户名不为空")
                         @Size(min = 3, max = 10, message = "用户名不合法，用户名长度应为3到10位")
                         @Pattern(regexp = "^\\w+$", message = "用户名不合法，用户名只能由字母、数字或下划线组成")
                                 String username,
                         @RequestParam(name = "password")
                         @NotEmpty(message = "密码不为空")
                         @Size(min = 5, max = 12, message = "密码不合法，密码长度应为5到12位")
                                 String password) {
        return userService.login(username, password);
    }
}
