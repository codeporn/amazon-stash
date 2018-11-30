package de.kodestruktor.amazon.stash.model;


import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.PostConstruct;
import javax.persistence.Basic;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class Persona extends AbstractDatedEntity {

  @Id
  @Email
  private String email;

  @NotBlank
  @Basic(optional = false)
  private String secret;

  @PostConstruct
  private void setOtpSecret() {
    this.secret = Base32.random();
  }
}
