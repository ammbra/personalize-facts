package org.acme.experiments.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDTO {

    public String _id;

    public Name name;

    public String photo;
}

@NoArgsConstructor
class Name {
    public String first;
    public String last;
}