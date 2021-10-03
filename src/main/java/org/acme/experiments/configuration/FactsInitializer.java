package org.acme.experiments.configuration;

import io.quarkus.arc.profile.UnlessBuildProfile;
import io.quarkus.runtime.Startup;
import lombok.SneakyThrows;
import org.acme.experiments.dto.FactDTO;
import org.acme.experiments.external.FactsService;
import org.acme.experiments.mapper.PersonalizedFactsMapper;
import org.acme.experiments.model.PersonalizedFact;
import org.acme.experiments.service.PersonalizedFactsService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;

@Startup
@ApplicationScoped
//@UnlessBuildProfile("test")
public class FactsInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FactsInitializer.class);

    @Inject
    @RestClient
    FactsService factsService;

    @Inject
    PersonalizedFactsService personalizedFactsService;

    @ConfigProperty(name = "initial-capacity")
    Integer initialCapacity;


    @SneakyThrows
    @PostConstruct
    public void init() {
        LOGGER.debug("Initializing the db from external service");
        PersonalizedFactsMapper personalizedMapper = new PersonalizedFactsMapper();
        Set<FactDTO> facts = factsService.getByTypeAsync("cat", initialCapacity);
        Set<PersonalizedFact> personalizedFacts = personalizedMapper.mapFromDTO(facts);
        personalizedFactsService.setup(personalizedFacts);
        LOGGER.debug("End initialization of the db ");
    }
}