package com.erickWck.modules.company.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(name = "nome")
    @Schema(example = "Tech Solutions Ltda", requiredMode = REQUIRED, minLength = 5, maxLength = 70,
            description = "Nome da empresa")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaço.")
    @Schema(example = "tech_solutions", requiredMode = REQUIRED, minLength = 5, maxLength = 30,
            description = "Vai ser o Login da empresa")
    private String username;

    @Email(message = "O campo deve conter um email válido exemplo nome@gmail.com ")
    @Schema(example = "contato@techsolutions.com", requiredMode = REQUIRED, minLength = 5, maxLength = 100,
            description = "Vai ser o email da empresa no sistema.")
    private String email;

    @NotBlank(message = "Insira a senha.")
    @Length(min = 10, max = 100, message = "A senha deve conter entre {min} e {max} caracteres.")
    @Schema(example = "SuperSecurePass2024", requiredMode = REQUIRED, minLength = 10, maxLength = 100,
            description = "Vai a senha da empresa para logar no sistema. '/'A senha é criptografada.")
    private String password;

    @Schema(example = "https://www.techsolutions.com", minLength = 10, maxLength = 120,
            description = "Site da empresa para ser exibido.")
    private String website;

    @Schema(example = "Uma empresa de tecnologia especializada em soluções inovadoras para o mercado digital.",
            minLength = 100, maxLength = 800,
            description = "Faça uma descrição da empresa.")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
