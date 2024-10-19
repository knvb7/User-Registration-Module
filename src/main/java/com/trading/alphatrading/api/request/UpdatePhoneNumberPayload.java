package com.trading.alphatrading.api.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdatePhoneNumberPayload {
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    private String oldPhoneNumber;
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    private String newPhoneNumber;
    private String password;
}
