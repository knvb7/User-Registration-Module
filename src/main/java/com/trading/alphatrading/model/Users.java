package com.trading.alphatrading.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "active")
    private boolean active;

    public Users(@Email(message = "Invalid email format.") String emailId, @NotBlank(message = "Password cannot be blank.") @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters long.") String password, String phoneNumber, String lastName, @NotBlank(message = "First name cannot be blank.") String firstName, boolean b) {
    }
}
