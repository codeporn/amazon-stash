package de.kodestruktor.amazon.stash.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import de.kodestruktor.amazon.stash.model.Persona;
import reactor.core.publisher.Mono;

/**
 * @author Christoph Wende
 */
@Repository
public interface PersonaRepo extends ReactiveCrudRepository<Persona, String> {

  Mono<Persona> findByEmail(final String email);

}
