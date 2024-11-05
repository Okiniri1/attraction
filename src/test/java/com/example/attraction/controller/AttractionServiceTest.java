package com.example.attraction.controller;

import com.example.attraction.dto.AttractionDTO;
import com.example.attraction.entity.Attraction;
import com.example.attraction.mapper.AttractionMapper;
import com.example.attraction.repository.AttractionRepository;
import com.example.attraction.service.AttractionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AttractionServiceTest {

    @Mock
    private AttractionRepository repository;

    @Mock
    private AttractionMapper mapper;

    @InjectMocks
    private AttractionService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAttraction() {
        AttractionDTO dto = new AttractionDTO();
        dto.setName("Eiffel Tower");

        Attraction attraction = new Attraction();
        attraction.setId(1L);
        attraction.setName("Eiffel Tower");

        when(mapper.toEntity(dto)).thenReturn(attraction);
        when(repository.save(attraction)).thenReturn(attraction);
        when(mapper.toDto(attraction)).thenReturn(dto);

        AttractionDTO savedDto = service.createAttraction(dto);

        assertEquals("Eiffel Tower", savedDto.getName());
        verify(repository, times(1)).save(any(Attraction.class));
    }

    @Test
    public void testGetAttractionById() {
        Attraction attraction = new Attraction();
        attraction.setId(1L);
        attraction.setName("Eiffel Tower");

        when(repository.findById(1L)).thenReturn(Optional.of(attraction));
        when(mapper.toDto(attraction)).thenReturn(new AttractionDTO());

        AttractionDTO dto = service.getAttractionById(1L);

        assertNotNull(dto);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteAttraction() {
        service.deleteAttraction(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
