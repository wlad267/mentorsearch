package com.bluementors.data;

import com.bluementors.User;

public class UserData {

    public static User invalidUser = new User();

    public static User validUser = new User.Builder()
            .authenticationString("lulu123")
            .email("lulu@lulu.org")
            .firstName("lulu")
            .lastName("lulu")
            .build();
}
