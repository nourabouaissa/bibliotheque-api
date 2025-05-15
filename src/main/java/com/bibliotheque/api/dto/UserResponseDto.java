package com.bibliotheque.api.dto;

public class UserResponseDto {
    private Long id;
    private String username;
    private String role;

    public UserResponseDto(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // getters et setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "UserResponseDto{id=" + id + ", username='" + username + "', role='" + role + "'}";
    }

}
