package com.joshuafeld.athly.user.service;

import com.joshuafeld.athly.user.dto.UserPatchDto;
import com.joshuafeld.athly.user.dto.UserPostDto;
import com.joshuafeld.athly.user.dto.UserDto;
import com.joshuafeld.athly.user.dto.UserPutDto;
import com.joshuafeld.athly.user.model.User;
import com.joshuafeld.athly.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * A user service.
 */
@Service
public class UserService {

    private final UserRepository repository;

    /**
     * Creates an instance of a {@code UserService} class.
     *
     * @param repository the value for the {@code repository} component
     */
    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new user.
     *
     * @param dto the data for the user
     * @return the data of the user
     */
    @Transactional
    public UserDto post(final UserPostDto dto) {
        return toDto(repository.save(new User(dto.username(), dto.email(),
                dto.firstName(), dto.lastName())));
    }

    /**
     * Returns the data of all users.
     *
     * @return a list of all users' data
     */
    @Transactional(readOnly = true)
    public List<UserDto> get() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    /**
     * Returns the data of the user with the given id.
     *
     * @param id the id of the user
     * @return the data of the user
     */
    @Transactional(readOnly = true)
    public UserDto get(final Long id) {
        return toDto(repository.requireById(id));
    }

    /**
     * Partially updates the data of the user with the given id.
     *
     * @param id the id of the user
     * @param dto the data for the user
     * @return the data of the user
     */
    @Transactional
    public UserDto patch(final Long id, final UserPatchDto dto) {
        User user = repository.requireById(id);
        Optional.ofNullable(dto.username()).ifPresent(user::username);
        Optional.ofNullable(dto.email()).ifPresent(user::email);
        Optional.ofNullable(dto.firstName()).ifPresent(user::firstName);
        Optional.ofNullable(dto.lastName()).ifPresent(user::lastName);
        return toDto(repository.save(user));
    }

    /**
     * Updates the data of the user with the given id.
     *
     * @param id the id of the user
     * @param dto the data for the user
     * @return the data of the user
     */
    @Transactional
    public UserDto put(final Long id, final UserPutDto dto) {
        User user = repository.requireById(id);
        user.username(dto.username());
        user.email(dto.email());
        user.firstName(dto.firstName());
        user.lastName(dto.lastName());
        return toDto(repository.save(user));
    }

    /**
     * Deletes the data of the user with the given id.
     *
     * @param id the id of the user
     */
    @Transactional
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    private UserDto toDto(final User user) {
        return new UserDto(
                user.id(),
                user.username(),
                user.email(),
                user.firstName(),
                user.lastName()
        );
    }
}
