package com.joshuafeld.athly.user.controller;

import com.joshuafeld.athly.user.dto.*;
import com.joshuafeld.athly.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * An authentication controller.
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;

    /**
     * Logs a user in.
     *
     * @param dto the data for the login
     * @return the data of the token
     */
    @PostMapping("/login")
    public LoginDto post(@RequestBody @Valid final LoginPostDto dto) {
        return service.login(dto);
    }

    /**
     * Registers a new user.
     *
     * @param dto the data for the registration
     * @return the data of the user
     */
    @PostMapping("/register")
    public UserDto post(@RequestBody @Valid final RegisterPostDto dto) {
        return service.register(dto);
    }

    /**
     * Refreshes the token.
     *
     * @param dto the data for the refresh
     * @return the data of the token
     */
    @PostMapping("/refresh")
    public RefreshDto refresh(@RequestBody @Valid final RefreshPostDto dto) {
        return service.refresh(dto);
    }

    /**
     * Logs a user out.
     *
     * @param dto the data for the logout
     */
    @PostMapping("/logout")
    public void logout(@RequestBody @Valid final LogoutPostDto dto) {
        service.logout(dto);
    }
}
