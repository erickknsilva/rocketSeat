package com.erickWck.modules.company.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Data
@Entity(name = "job")
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Vaga para atuar como Desenvolvedor")
    private String description;

    @Schema(example = "Plano de saúde, Gympass, VA/VR ")
    private String beneficios;

    @NotBlank(message = "Esse campo é obrigatorio")
    @Schema(example = "Nivel Senior)")
    private String level;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", nullable = false)
    private UUID company_Id;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    public JobEntity(String level, String beneficios, String description, UUID companyId) {
        this.level = level;
        this.beneficios = beneficios;
        this.description = description;
        this.company_Id = companyId;
    }
}
