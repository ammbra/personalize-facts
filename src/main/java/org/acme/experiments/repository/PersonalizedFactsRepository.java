package org.acme.experiments.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.experiments.model.PersonalizedFact;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonalizedFactsRepository implements PanacheRepository<PersonalizedFact> {

    @Transactional
    public Set<PersonalizedFact> findBySource(String source, Integer size) {
        return find("source=?1", source).page(0, size+1).stream().collect(Collectors.toSet());
    }

}