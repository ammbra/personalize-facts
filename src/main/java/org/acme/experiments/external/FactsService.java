package org.acme.experiments.external;

import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import org.acme.experiments.dto.FactDTO;
import org.acme.experiments.dto.PersonalizedFactDTO;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletionStage;


@Path("/facts")
@RegisterRestClient
public interface FactsService {
    static final Logger LOGGER = LoggerFactory.getLogger(FactsService.class);

    @GET
    @Produces("application/json")
    @Fallback(FactFallback.class)
    Set<FactDTO> getByType(@QueryParam("animal_type") String animalType);

    @GET
    @Path("random")
    @Produces("application/json")
    @Fallback(FactFallback.class)
    Set<FactDTO> getByTypeAsync(@QueryParam("animal_type") String animalType, @QueryParam("amount") int amount);

    @GET
    @Path("{factID}")
    @Produces("application/json")
    @CacheResult(cacheName = "animal-fact-async")
    CompletionStage<PersonalizedFactDTO> getByFactIDAsync(@CacheKey @PathParam("factID") String factID);

    public static class FactFallback implements FallbackHandler<Set<FactDTO>> {

        private static final FactDTO EMPTY_PERSONALIZED_FACT = FactDTO.empty();
        @Override
        public Set<FactDTO> handle(ExecutionContext context) {
            return Collections.singleton(EMPTY_PERSONALIZED_FACT);
        }

    }

}
