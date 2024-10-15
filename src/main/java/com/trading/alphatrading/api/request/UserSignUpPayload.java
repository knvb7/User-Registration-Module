package com.trading.alphatrading.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignUpPayload {
    @NotBlank(message = "First name cannot be blank.")
    private String firstName;
    private String lastName;
    @Email(message = "Invalid email format.")
    private String emailId;
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    private String phoneNumber;
    @NotBlank(message = "Password cannot be blank.")
    @Size(min=6, max=20, message = "Password must be between 6 and 20 characters long.")
    private String password;
}
