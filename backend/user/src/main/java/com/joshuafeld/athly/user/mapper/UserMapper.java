package com.joshuafeld.athly.user.mapper;

import com.joshuafeld.athly.common.mapper.Mapper;
import com.joshuafeld.athly.user.dto.UserDto;
import com.joshuafeld.athly.user.model.User;
import org.springframework.stereotype.Component;

/**
 * A data transfer object mapper for users.
 */
@Component
public class UserMapper implements Mapper<User, UserDto> {

    /**
     * Converts a user entity to a data transfer object.
     *
     * @param user the user entity
     * @return the data transfer object
     */
    @Override
    public UserDto toDto(final User user) {
        return new UserDto(
                user.id(),
                user.username(),
                user.email(),
                user.firstName(),
                user.lastName()
        );
    }
}
