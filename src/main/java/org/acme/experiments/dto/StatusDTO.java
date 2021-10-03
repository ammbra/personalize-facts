package org.acme.experiments.dto;

public record StatusDTO (boolean verified, int sentCount, String feedback) {
}
