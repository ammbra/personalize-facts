package org.acme.experiments.external;

import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import org.acme.experiments.dto.FactDTO;
import org.acme.experiments.dto.PersonalizedFactDTO;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Path("/facts")
@RegisterRestClient
public interface FactsService {
    Logger LOGGER = LoggerFactory.getLogger(FactsService.class);
    FactDTO EMPTY_FACT = FactDTO.empty();

    @GET
    @Produces("application/json")
    @Fallback(FactFallback.class)
    Set<FactDTO> getByType(@QueryParam("animal_type") String animalType);

    @GET
    @Path("random")
    @Produces("application/json")
    @Fallback(fallbackMethod = "fallbackFacts")
    Set<FactDTO> getByTypeAsync(@QueryParam("animal_type") String animalType, @QueryParam("amount") int amount);

    @GET
    @Path("{factID}")
    @Produces("application/json")
    @Retry(maxRetries =1, delay = 1000)
    @Fallback(AsyncFactFallback.class)
    @CacheResult(cacheName = "animal-fact-async")
    CompletionStage<PersonalizedFactDTO> getByFactIDAsync(@CacheKey @PathParam("factID") String factID);

    class FactFallback implements FallbackHandler<Set<FactDTO>> {

        @Override
        public Set<FactDTO> handle(ExecutionContext context) {
            return Set.of(EMPTY_FACT);
        }

    }

    class AsyncFactFallback implements FallbackHandler<CompletionStage<PersonalizedFactDTO>> {

        private static final PersonalizedFactDTO EMPTY_PERSONALIZED_FACT = PersonalizedFactDTO.empty();
        @Override
        public CompletionStage<PersonalizedFactDTO> handle(ExecutionContext context) {
            return CompletableFuture.supplyAsync(() -> EMPTY_PERSONALIZED_FACT);
        }

    }

    default Set<FactDTO> fallbackFacts(String type, int amount) {
        LOGGER.debug("Falling back to RecommendationResource#fallbackRecommendations()");
        return Set.of(EMPTY_FACT);
    }
}
