package com.synchrony.usermanagement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String emailId;
    private String profession;
    private String imageLink;
}
