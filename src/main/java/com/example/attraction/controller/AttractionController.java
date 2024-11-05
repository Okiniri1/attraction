package com.example.attraction.controller;

import com.example.attraction.dto.AttractionDTO;
import com.example.attraction.service.AttractionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/attraction")
public class AttractionController {
    private final AttractionService service;

    public AttractionController(AttractionService service) {
        this.service = service;
    }

    @PostMapping
    public AttractionDTO createAttraction(@RequestBody AttractionDTO dto) {
        return service.createAttraction(dto);
    }

    @GetMapping("/{id}")
    public AttractionDTO getAttractionById(@PathVariable Long id) {
        return service.getAttractionById(id);
    }

    @PutMapping("/{id}")
    public AttractionDTO updateAttraction(@PathVariable Long id, @RequestBody AttractionDTO dto) {
        return service.updateAttraction(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAttraction(@PathVariable Long id) {
        service.deleteAttraction(id);
    }

    @GetMapping
    public List<AttractionDTO> getAllAttractions() {
        return service.getAllAttractions();
    }

}
