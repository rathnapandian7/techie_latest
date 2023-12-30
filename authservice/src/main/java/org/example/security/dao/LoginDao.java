package org.example.security.dao;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDao {

    @NotNull(message = "UserName cannot be Empty")
    private String userName;

    @NotNull(message = "Password cannot be Empty")
    private String password;

    private Integer isRememberMe = 0;
}
