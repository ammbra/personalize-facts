package org.acme.experiments.mapper;

import org.acme.experiments.dto.FactDTO;
import org.acme.experiments.dto.PersonalizedFactDTO;
import org.acme.experiments.dto.StatusDTO;
import org.acme.experiments.model.PersonalizedFact;
import org.acme.experiments.model.Status;

import java.util.HashSet;
import java.util.Set;

public class PersonalizedFactsMapper {

    public Set<PersonalizedFact> mapFromDTO(Set<FactDTO> facts) {
        Set<PersonalizedFact> personalizedFacts = new HashSet<>(facts.size());
        for (FactDTO fact : facts) {
            PersonalizedFact personalizedFact = new PersonalizedFact();
            personalizedFact.setFactId(fact._id);
            personalizedFact.setRandomness(Math.random());
            personalizedFact.setStatus(mapFromDTO(fact.statusDTO));
            personalizedFact.setDeleted(fact.deleted);
            personalizedFact.setType(fact.type);
            personalizedFact.setSource(fact.source);
            personalizedFact.setCreatedAt(fact.createdAt);
            personalizedFact.setUpdatedAt(fact.updatedAt);
            personalizedFact.setText(fact.text);
            personalizedFacts.add(personalizedFact);
        }
        return personalizedFacts;
    }

    private Status mapFromDTO(StatusDTO statusDTO) {
        Status status = new Status();
        if ( statusDTO != null ) {
            status.setFeedback(statusDTO.feedback());
            status.setSentCount(statusDTO.sentCount());
            status.setVerified(statusDTO.verified());
        }
        return status;
    }

    private StatusDTO mapToDTO(Status status) {
        return new StatusDTO(status.isVerified(), status.getSentCount(), status.getFeedback());
    }

    public Set<PersonalizedFactDTO> mapToDTO(Set<PersonalizedFact> facts) {
        Set<PersonalizedFactDTO> dtos = new HashSet<>(facts.size());
        for (PersonalizedFact fact : facts) {
            PersonalizedFactDTO dto = new PersonalizedFactDTO();
            dto._id = fact.getFactId();
            dto.createdAt = fact.getCreatedAt();
            dto.statusDTO = (fact.getStatus() != null) ? mapToDTO(fact.getStatus()) : null;
            dto.randomness = fact.getRandomness();
            dto.updatedAt = fact.getUpdatedAt();
            dto.source = fact.getSource();
            dto.text = fact.getText();
            dto.deleted = fact.isDeleted();
            dtos.add(dto);
        }
        return dtos;
    }
}
