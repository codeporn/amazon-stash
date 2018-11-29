package de.kodestruktor.amazon.stash.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class Persona extends AbstractDatedEntity {

  @Id
  private String email;

}
