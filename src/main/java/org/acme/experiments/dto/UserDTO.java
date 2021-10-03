package org.acme.experiments.dto;

import lombok.NoArgsConstructor;

public record UserDTO(String _id, Name name, String photo) {
}

@NoArgsConstructor
class Name {
    public String first;
    public String last;
}