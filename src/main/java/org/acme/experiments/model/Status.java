package org.acme.experiments.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class Status {
   @Getter
   @Setter
   private boolean verified;

   @Getter
   @Setter
   private int sentCount;

   @Getter
   @Setter
   private String feedback;
}
