package com.example.attraction.controller;

import com.example.attraction.entity.Attraction;
import com.example.attraction.repository.AttractionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AttractionRepositoryTest {

    @Autowired
    private AttractionRepository repository;

    @Test
    public void testCreateAndFindAttraction() {
        Attraction attraction = new Attraction();
        attraction.setName("Eiffel Tower");
        attraction.setDescription("A famous landmark in Paris");
        attraction.setLocation("Paris, France");
        attraction.setRating(4.7);

        Attraction savedAttraction = repository.save(attraction);
        assertThat(savedAttraction.getId()).isNotNull();

        Attraction foundAttraction = repository.findById(savedAttraction.getId()).orElse(null);
        assertThat(foundAttraction).isNotNull();
        assertThat(foundAttraction.getName()).isEqualTo("Eiffel Tower");
    }
}

