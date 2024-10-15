package com.trading.alphatrading.controller;

import com.trading.alphatrading.api.response.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/signIn/")
public class SignInController {
    @GetMapping("/googleSignIn")
    public ResponseEntity<ApiResponse<String>>
}
