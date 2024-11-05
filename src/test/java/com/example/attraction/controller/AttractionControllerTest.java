package com.example.attraction.controller;

import com.example.attraction.dto.AttractionDTO;
import com.example.attraction.service.AttractionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AttractionController.class)
public class AttractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttractionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAttraction() throws Exception {
        AttractionDTO dto = new AttractionDTO();
        dto.setName("Eiffel Tower");

        when(service.createAttraction(any(AttractionDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/attraction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eiffel Tower"));
    }

    @Test
    public void testGetAttractionById() throws Exception {
        AttractionDTO dto = new AttractionDTO();
        dto.setId(1L);
        dto.setName("Eiffel Tower");

        when(service.getAttractionById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/attraction/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eiffel Tower"));
    }

    @Test
    public void testDeleteAttraction() throws Exception {
        doNothing().when(service).deleteAttraction(1L);

        mockMvc.perform(delete("/api/attraction/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteAttraction(1L);
    }
}
