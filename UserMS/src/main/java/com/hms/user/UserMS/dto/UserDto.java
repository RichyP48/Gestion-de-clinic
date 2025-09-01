package com.hms.user.UserMS.dto;

import com.hms.user.UserMS.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "password is mandatory")
    private String password;
    @Email(message = "email should be valid")
    @NotBlank(message = "email is mandatory")
    private String email;
    private Roles role;
    private Long profileId;

    public User toEntity() {
        return new User(this.id, this.name, this.password, this.email, this.role, this.profileId);
    }
}
