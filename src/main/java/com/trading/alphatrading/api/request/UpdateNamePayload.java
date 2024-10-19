package com.trading.alphatrading.api.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateNamePayload {
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    private String phoneNumber;
    private String password;
}