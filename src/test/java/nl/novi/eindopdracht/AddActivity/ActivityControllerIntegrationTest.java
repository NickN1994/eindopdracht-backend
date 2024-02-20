package nl.novi.eindopdracht.AddActivity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ActivityControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    @BeforeEach
    void setUp() {
        Activity activity = new Activity(null, "Adem coaching", 8, "Kirstie", LocalDate.parse("2024-01-09"), "van 10u tot 15u", "Dit is een test voor deze ademcirkel die wordt gegeven op 9 januari aanstaande");
        activityRepository.save(activity);
    }

    @AfterEach
    void tearDown() {

        activityRepository.deleteAll();
    }

    @Test
    void getAllActivity_success() throws Exception {
        mockMvc.perform(get("/activities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.[0].name").value("Adem coaching"))
                .andExpect(jsonPath("$.[0].participants").value(8))
                .andExpect(jsonPath("$.[0].teacher").value("Kirstie"))
                .andExpect(jsonPath("$.[0].date").value("2024-01-09"))
                .andExpect(jsonPath("$.[0].time").value("van 10u tot 15u"))
                .andExpect(jsonPath("$.[0].activityInfo").value("Dit is een test voor deze ademcirkel die wordt gegeven op 9 januari aanstaande"));
    }
}
