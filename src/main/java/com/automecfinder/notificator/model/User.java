package com.automecfinder.notificator.model;

import com.automecfinder.notificator.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class User {

    private String name;

    private String lastName;

    private String email;

    private String password;

    private UserStatus status;

    private String activationToken;

    private String roles;
}
