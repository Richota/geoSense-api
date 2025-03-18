package com.talentofuturo.geoSense_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admins")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    // Método para encriptar la contraseña antes de guardar
    public void setPassword(String password) {

        this.password = new BCryptPasswordEncoder().encode(password);
    }
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Company> companies = new ArrayList<>();
}
