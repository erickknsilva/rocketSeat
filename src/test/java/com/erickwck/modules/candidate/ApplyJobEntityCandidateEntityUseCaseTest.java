package com.erickwck.modules.candidate;

import com.erickwck.infrastructure.exceptions.CandidateNotFoundException;
import com.erickwck.infrastructure.exceptions.JobNotFoundException;
import com.erickwck.modules.candidate.entity.ApplyJobEntity;
import com.erickwck.modules.candidate.entity.CandidateEntity;
import com.erickwck.modules.candidate.repository.ApplyJobRepository;
import com.erickwck.modules.candidate.repository.CandidateRepository;
import com.erickwck.modules.candidate.usecases.ApplyJobCandidateUseCase;
import com.erickwck.modules.company.entity.JobEntity;
import com.erickwck.modules.company.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class ApplyJobEntityCandidateEntityUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;


    private CandidateEntity candidate;

    @BeforeEach
    public void setup() {
        var idCandidate = UUID.randomUUID();
        candidate = CandidateEntity.builder().id(idCandidate).name("erick").username("erickWck").email("erickWck@gmail.com").build();
    }


    @Test
    @DisplayName("Deve lançar uma exceção CandidateNotFoundException se o candidato não existir.")
    public void shouldThrowCandidateNotFoundExceptionWhenCandidateNotExist() {


        assertThatExceptionOfType(CandidateNotFoundException.class)
                .isThrownBy(() -> applyJobCandidateUseCase.execute(candidate.getId(), null))
                .withMessage("Candidate not found");

    }

    @Test
    @DisplayName("Deve lançar uma exceção JobNotFoundException, se vaga não existir.")
    public void shouldThrowJobNotFoundExceptionWhenCandidateNotExist() {

        when(candidateRepository.findById(candidate.getId())).thenReturn(Optional.of(candidate));

        assertThatExceptionOfType(JobNotFoundException.class)
                .isThrownBy(() -> applyJobCandidateUseCase.execute(candidate.getId(), null))
                .withMessage("Job not found");
    }

    @Test
    @DisplayName("Cria uma candidatura para a vaga")
    public void shouldCreateANewApplyJob() {

        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateID(candidate.getId()).jobID(idJob).build();
        var applyCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(candidate.getId())).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyCreated);

        var result = applyJobCandidateUseCase.execute(candidate.getId(), idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }
}
