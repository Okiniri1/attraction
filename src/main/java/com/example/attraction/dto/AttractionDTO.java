package com.example.attraction.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttractionDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String location;
    @NotNull
    private Double rating;
}

