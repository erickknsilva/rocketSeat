package com.erickWck.modules.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(name = "nome")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaço.")
    private String username;

    @Email(message = "O campo deve conter um email válido exemplo nome@gmail.com ")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre {min} e {max} caracteres.")
    private String password;

    private String website;
    
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
