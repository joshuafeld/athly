package com.joshuafeld.athly.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A user entity.
 */
@Entity
@Table(name = "users")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;

    private String lastName;

    /**
     * Creates an instance of a {@code User} class.
     */
    public User() {
    }

    /**
     * Creates an instance of a {@code User} class.
     *
     * @param username the value for the {@code username} component
     * @param email the value for the {@code email} component
     * @param firstName the value for the {@code firstName} component
     * @param lastName the value for the {@code lastName} component
     */
    public User(final String username,
                final String email,
                final String firstName,
                final String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the value of the {@code id} component.
     *
     * @return the value of the {@code id} component
     */
    public Long id() {
        return id;
    }

    /**
     * Returns the value of the {@code username} component.
     *
     * @return the value of the {@code username} component
     */
    public String username() {
        return username;
    }

    /**
     * Sets the value of the {@code username} component.
     *
     * @param username the value for the {@code username} component
     */
    public void username(final String username) {
        this.username = username;
    }

    /**
     * Returns the value of the {@code email} component.
     *
     * @return the value of the {@code email} component
     */
    public String email() {
        return email;
    }

    /**
     * Sets the value of the {@code email} component.
     *
     * @param email the value for the {@code email} component
     */
    public void email(final String email) {
        this.email = email;
    }

    /**
     * Returns the value of the {@code firstName} component.
     *
     * @return the value of the {@code firstName} component
     */
    public String firstName() {
        return firstName;
    }

    /**
     * Sets the value of the {@code firstName} component.
     *
     * @param firstName the value for the {@code firstName} component
     */
    public void firstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the value of the {@code lastName} component.
     *
     * @return the value of the {@code lastName} component
     */
    public String lastName() {
        return lastName;
    }

    /**
     * Sets the value of the {@code lastName} component.
     *
     * @param lastName the value for the {@code lastName} component
     */
    public void lastName(final String lastName) {
        this.lastName = lastName;
    }
}
