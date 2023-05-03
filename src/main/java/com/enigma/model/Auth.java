package com.enigma.model;

import com.enigma.util.Role;

import javax.persistence.*;

@Entity
@Table(name="m_auth")
public class Auth {
    @Id
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean isActive;

    @Override
    public String toString() {
        return "Auth{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public Auth setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Auth setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public Auth setRole(Role role) {
        this.role = role;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public Auth setActive(boolean active) {
        isActive = active;
        return this;
    }
}
