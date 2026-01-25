package com.joshuafeld.athly.user.controller;

import com.joshuafeld.athly.user.dto.UserPatchDto;
import com.joshuafeld.athly.user.dto.UserPostDto;
import com.joshuafeld.athly.user.dto.UserDto;
import com.joshuafeld.athly.user.dto.UserPutDto;
import com.joshuafeld.athly.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A user controller.
 */
@RestController
@RequestMapping("/users")
public final class UserController {

    private final UserService service;

    /**
     * Creates an instance of a {@code UserController} class.
     *
     * @param service the value for the {@code service} component
     */
    public UserController(final UserService service) {
        this.service = service;
    }

    /**
     * Creates a new user.
     *
     * @param dto the data for the user
     * @return the data of the user
     */
    @PostMapping
    public UserDto post(@RequestBody @Valid final UserPostDto dto) {
        return service.post(dto);
    }

    /**
     * Returns the data of all users.
     *
     * @return a list of all users' data
     */
    @GetMapping
    public List<UserDto> get() {
        return service.get();
    }

    /**
     * Returns the data of the user with the given id.
     *
     * @param id the id of the user
     * @return the data of the user
     */
    @GetMapping("/{id}")
    public UserDto get(@PathVariable final Long id) {
        return service.get(id);
    }

    /**
     * Partially updates the data of the user with the given id.
     *
     * @param id the id of the user
     * @param dto the data for the user
     * @return the data of the user
     */
    @PatchMapping("/{id}")
    public UserDto patch(@PathVariable final Long id,
                         @RequestBody @Valid final UserPatchDto dto) {
        return service.patch(id, dto);
    }

    /**
     * Updates the data of the user with the given id.
     *
     * @param id the id of the user
     * @param dto the data for the user
     * @return the data of the user
     */
    @PutMapping("/{id}")
    public UserDto put(@PathVariable final Long id,
                       @RequestBody @Valid final UserPutDto dto) {
        return service.put(id, dto);
    }

    /**
     * Deletes the data of the user with the given id.
     *
     * @param id the id of the user
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        service.delete(id);
    }
}
