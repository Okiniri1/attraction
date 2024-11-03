package com.example.attraction.mapper;

import com.example.attraction.dto.AttractionDTO;
import com.example.attraction.entity.Attraction;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AttractionMapper {
    AttractionDTO toDto(Attraction attraction);
    Attraction toEntity(AttractionDTO attractionDTO);
}
