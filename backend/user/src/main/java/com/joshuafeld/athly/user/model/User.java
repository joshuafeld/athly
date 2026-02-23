package com.joshuafeld.athly.user.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * A user entity.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    private Set<Role> roles;

    private String firstName;

    private String lastName;

    /**
     * Creates an instance of a {@code User} class.
     *
     * @param username the value for the {@code username} component
     * @param email the value for the {@code email} component
     * @param password the value for the {@code password} component
     * @param roles the value for the {@code roles} component
     * @param firstName the value for the {@code firstName} component
     * @param lastName the value for the {@code lastName} component
     */
    public User(final String username,
                final String email,
                final String password,
                final Set<Role> roles,
                final String firstName,
                final String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
