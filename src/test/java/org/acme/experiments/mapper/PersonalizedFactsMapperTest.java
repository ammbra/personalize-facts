package org.acme.experiments.mapper;

import org.acme.experiments.dto.FactDTO;
import org.acme.experiments.dto.PersonalizedFactDTO;
import org.acme.experiments.dto.StatusDTO;
import org.acme.experiments.model.PersonalizedFact;
import org.acme.experiments.model.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PersonalizedFactsMapperTest {
    FactDTO dto;

    PersonalizedFact fact;

    PersonalizedFactsMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new PersonalizedFactsMapper();
        dto = new FactDTO();
        dto._id = UUID.randomUUID().toString();
        dto.createdAt = new Date();
        dto.statusDTO = new StatusDTO();
        dto.statusDTO.sentCount = 4;
        dto.source = "api";
        dto.type = "cat";
        dto.text = "A fact about cats";
        dto.used = true;
        dto.updatedAt = new Date();
        dto.user = "user";

        fact = new PersonalizedFact();
        fact.setUpdatedAt(new Date());
        fact.setFactId(UUID.randomUUID().toString());
        fact.setText("A fact about cats");
        fact.setSource("api");
        fact.setStatus(new Status());
        fact.setRandomness(Math.random());
        fact.setType("cat");
    }

    @Test
    void shouldMapFromDTO() {
        Set<FactDTO> facts = new HashSet<>();
        facts.add(dto);
        Set<PersonalizedFact> personalizedFacts = mapper.mapFromDTO(facts);
        assertNotNull(personalizedFacts);
        assertEquals(1, personalizedFacts.size());
    }

    @Test
    void shouldMapToDTO() {
        Set<PersonalizedFact> personalizedFacts = new HashSet<>();
        personalizedFacts.add(fact);
        Set<PersonalizedFactDTO> dtos = mapper.mapToDTO(personalizedFacts);
        assertNotNull(dtos);
        assertEquals(1, dtos.size());

    }

    @AfterEach
    void tearDown() {
        dto = null;
        mapper = null;
        fact = null;
    }
}