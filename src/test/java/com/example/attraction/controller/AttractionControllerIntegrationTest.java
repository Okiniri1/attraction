package com.example.attraction.controller;

import com.example.attraction.dto.AttractionDTO;
import com.example.attraction.repository.AttractionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AttractionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AttractionRepository repository;

    @Test
    public void shouldCreateAndFetchAttraction() throws Exception {
        AttractionDTO attraction = new AttractionDTO();
        attraction.setName("Eiffel Tower");
        attraction.setDescription("A famous landmark in Paris");
        attraction.setLocation("Paris, France");
        attraction.setRating(4.7);

        mockMvc.perform(post("/api/attractions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(attraction)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/attractions/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value("Eiffel Tower"));
    }
}
