package org.acme.experiments;

import org.acme.experiments.external.FactsService;
import org.acme.experiments.service.PersonalizedFactsService;
import org.acme.experiments.dto.PersonalizedFactDTO;
import org.acme.experiments.dto.FactDTO;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@Path("/api")
//@GraphQLApi
public class PersonalizedFactsResource {

    @Inject
    @RestClient
    FactsService factsService;

    @Inject
    PersonalizedFactsService personalizedFactsService;

    @GET
    @Path("fact")
    @Produces(MediaType.APPLICATION_JSON)
//    @Query("allFactsByType")
    public Set<FactDTO> getByType(@QueryParam("type") String type) {
        return factsService.getByType(type);
    }

    @GET
    @Path("fact-async")
    @Produces(MediaType.APPLICATION_JSON)
//    @Query("allFactsByTypeAsync")
    public CompletionStage<Set<FactDTO>> getByTypeAsync(@QueryParam("type") String type) {
        return CompletableFuture.supplyAsync(() -> factsService.getByType(type));
    }

    @GET
    @Path("fact-type-async")
    @Produces(MediaType.APPLICATION_JSON)
//    @Query("allFactsByTypeAndAmount")
    public CompletionStage<Set<FactDTO>> getByTypeAndAmount(@QueryParam("type") String type, @QueryParam("amount") Integer amount) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> factsService.getByTypeAsync(type, amount));
    }

    @GET
    @Path("fact-async/{factId}/{randomness}")
//    @Query("animalFactById")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<PersonalizedFactDTO> getFactAsync(@PathParam("factId") String factId, @PathParam("randomness") Double randomness) {
        return factsService.getByFactIDAsync(factId);
    }

    @GET
    @Path("fact/{source}/{size}")
//    @Query("animalFactBySource")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<PersonalizedFactDTO> getPaginatedAnimalsBySource(@PathParam("source") String source, @PathParam("size")@DefaultValue("10") Integer size) {
        return personalizedFactsService.findBySource(source, size);
    }



}