package com.vrcs.livemenu.payloads;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String roles;
}
