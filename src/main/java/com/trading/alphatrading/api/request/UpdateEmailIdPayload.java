package com.trading.alphatrading.api.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateEmailIdPayload {
    private String oldEmailId;
    private String newEmailId;
    private String password;
}