package nl.novi.eindopdracht.Courses.Game.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import nl.novi.eindopdracht.Courses.Game.Models.Information;
import nl.novi.eindopdracht.Courses.Game.Repository.InformationRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class InformationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InformationRepository informationRepository;

    @BeforeEach
    void setUp() {
        informationRepository.deleteAll();
        Information information = new Information(null, "Test Title", "Test Content", "http://test.url/video");
        informationRepository.save(information);
    }

    @AfterEach
    void tearDown() {
        informationRepository.deleteAll();
    }

    @Test
    @WithMockUser // Simuleert een ingelogde gebruiker
    void getAllInformation_success() throws Exception {
        mockMvc.perform(get("/information")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Test Title"))
                .andExpect(jsonPath("$[0].content").value("Test Content"))
                .andExpect(jsonPath("$[0].videoUrl").value("http://test.url/video"));
    }
}