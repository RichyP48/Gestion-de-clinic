package com.hms.user.UserMS.entity;

import com.hms.user.UserMS.dto.Roles;
import com.hms.user.UserMS.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private Roles role;
    private Long profileId;

    public UserDto toDto() {
        return new UserDto(this.id, this.name, this.password, this.email, this.role, this.profileId);
    }
}
