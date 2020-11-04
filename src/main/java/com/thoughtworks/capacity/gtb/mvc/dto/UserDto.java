package com.thoughtworks.capacity.gtb.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;

    @NotEmpty(message = "用户名不为空")
    @Size(min = 3, max = 10, message = "用户名不合法，用户名长度应为3到10位")
    @Pattern(regexp = "^\\w+$", message = "用户名不合法，用户名只能由字母、数字或下划线组成")
    private String username;

    @NotEmpty(message = "密码不为空")
    @Size(min = 5, max = 12, message = "密码不合法，密码长度应为5到12位")
    private String password;

    @Email(message = "邮箱地址不合法")
    private String email;
}
