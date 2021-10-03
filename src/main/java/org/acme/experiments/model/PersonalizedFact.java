package org.acme.experiments.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Entity
public class PersonalizedFact  {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Getter
    @Setter
    private String factId;

    @Getter
    @Setter
    private Double randomness;

    @Setter
    @Getter
    private String type;

    @Setter
    @Getter
    @Column(columnDefinition="TEXT")
    private String text;

    @Setter
    @Getter
    private String source;

    @Embedded
    @Setter
    @Getter
    private Status status;

    @Setter
    @Getter
    private boolean deleted;

    @Setter
    @Getter
    private Date updatedAt;

    @Setter
    @Getter
    private Date createdAt;

    @Setter
    @Getter
    private boolean used;

}
