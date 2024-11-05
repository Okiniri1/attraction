package com.example.attraction.service;

import com.example.attraction.dto.AttractionDTO;
import com.example.attraction.entity.Attraction;
import com.example.attraction.mapper.AttractionMapper;
import com.example.attraction.repository.AttractionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AttractionService {
    private final AttractionRepository repository;
    private final AttractionMapper mapper;

    public AttractionService(AttractionRepository repository, AttractionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AttractionDTO> getAllAttractions() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public AttractionDTO createAttraction(AttractionDTO dto) {
        Attraction attraction = mapper.toEntity(dto);
        attraction = repository.save(attraction);
        return mapper.toDto(attraction);
    }

    public AttractionDTO getAttractionById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Attraction not found"));
    }

    public AttractionDTO updateAttraction(Long id, AttractionDTO dto) {
        Attraction attraction = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attraction not found"));
        attraction.setName(dto.getName());
        attraction.setDescription(dto.getDescription());
        attraction.setLocation(dto.getLocation());
        attraction.setRating(dto.getRating());
        attraction = repository.save(attraction);
        return mapper.toDto(attraction);
    }

    public void deleteAttraction(Long id) {
        repository.deleteById(id);
    }
}
