package org.acme.experiments.service;

import org.acme.experiments.dto.PersonalizedFactDTO;
import org.acme.experiments.mapper.PersonalizedFactsMapper;
import org.acme.experiments.model.PersonalizedFact;
import org.acme.experiments.repository.PersonalizedFactsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Set;

@Singleton
public class PersonalizedFactsService {

    @Inject
    PersonalizedFactsRepository repository;

    @Transactional
    public void setup(Set<PersonalizedFact> facts) {
        repository.persist(facts);
    }

    public Set<PersonalizedFactDTO> findBySource(String source, Integer size) {
        PersonalizedFactsMapper mapper = new PersonalizedFactsMapper();
        return mapper.mapToDTO(repository.findBySource(source, size));
    }

    public long count() {
        return repository.count();
    }
}
