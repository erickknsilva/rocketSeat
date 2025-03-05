package com.erickwck.modules.candidate.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(name = "nome")
    @Schema(example = "Erick", description = "Insira seu nome", requiredMode = RequiredMode.REQUIRED,
            minLength = 5, maxLength = 100)
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaço.")
    @Schema(example = "erickWck123", requiredMode = RequiredMode.REQUIRED)
    private String username;

    @Email(message = "O campo deve conter um email válido exemplo nome@gmail.com ")
    @Column(unique = true)
    @Schema(example = "erick@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "Insira um email que você usa.")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre {min} e {max} caracteres.")
    @Schema(example = "j@va@*()", requiredMode = RequiredMode.REQUIRED,
            minLength = 10, maxLength = 100, description = "Insira uma senha forte.")
    private String password;

    @Schema(example = "A vaga é HomeOffice, oferece Plano Saúde, VA/VR, Gympass, participação lucro",
            requiredMode = RequiredMode.REQUIRED, description = "A senha é criptografada.")
    private String description;

    @Schema(example = "Faça upload do seu curriculum")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
