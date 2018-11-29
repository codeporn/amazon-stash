package de.kodestruktor.amazon.stash.model;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

import lombok.Data;

/**
 * Ubiquitious entity superclass.
 *
 * @author Christoph Wende
 */
@Data
public abstract class AbstractDatedEntity {

  @CreatedDate
  private Date dateCreated;

  @LastModifiedDate
  private Date lastUpdated;

}
