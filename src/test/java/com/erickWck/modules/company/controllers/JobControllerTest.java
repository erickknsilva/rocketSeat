package com.erickWck.modules.company.controllers;

import com.erickWck.modules.company.dto.JobRequestDto;
import com.erickWck.modules.company.entity.CompanyEntity;
import com.erickWck.modules.company.repository.CompanyRepository;
import com.erickWck.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static com.erickWck.utils.TestUtils.generatedTokenUtils;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class JobControllerTest extends ConfigPostSqlContainerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldCreateANewJon() throws Exception {

        CompanyEntity company = CompanyEntity.builder()
                .description("Company_test")
                .email("teste@gmail.com")
                .password("1234567890")
                .name("company_name")
                .username("company_username")
                .build();

        company = companyRepository.saveAndFlush(company);

        JobRequestDto jobRequest = JobRequestDto.builder().beneficios("Beneficios teste")
                .description("descricao teste").level("levete teste").build();

        var result = mockMvc.perform(post("/company/job")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(jobRequest))
                        .header("Authorization", generatedTokenUtils(company.getId(),
                                "erickWck_!@#"))
                )
                .andExpect(status().isOk());

        System.out.println(result);
    }

    @Test
    public void shouldThrowCompanyNotFoundExceptionWhenCompanyDoesNotExist() throws Exception {

        JobRequestDto jobRequest = JobRequestDto.builder().beneficios("Beneficios teste")
                .description("descricao teste").level("levete teste").build();

        mockMvc.perform(post("/company/job")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(jobRequest))
                .header("Authorization", generatedTokenUtils(UUID.randomUUID(),
                        "erickWck_!@#"))
        ).andExpect(status().isBadRequest());

    }
}
