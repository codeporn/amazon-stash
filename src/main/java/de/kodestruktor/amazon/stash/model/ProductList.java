package de.kodestruktor.amazon.stash.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class ProductList extends AbstractDatedEntity {

}
