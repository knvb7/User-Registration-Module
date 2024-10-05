package com.trading.alphatrading.api.request;

import lombok.Data;

@Data
public class UserRegistrationPayload {
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private String password;
}
