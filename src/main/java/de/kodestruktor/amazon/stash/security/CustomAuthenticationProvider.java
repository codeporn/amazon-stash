package de.kodestruktor.amazon.stash.security;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.kodestruktor.amazon.stash.model.Persona;
import de.kodestruktor.amazon.stash.repo.PersonaRepo;

/**
 * @author Christoph Wende
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

  @Autowired
  private PersonaRepo personaRepo;

  @Override
  public Authentication authenticate(final Authentication auth) throws AuthenticationException {
    final Persona persona = this.personaRepo.findByEmail(auth.getName()).block();
    if ((persona == null)) {
      throw new BadCredentialsException("Invalid username or password");
    }

    final String verificationCode = ((CustomWebAuthenticationDetails) auth.getDetails()).getVerificationCode();
    final Totp totp = new Totp(persona.getSecret());
    if (!CustomAuthenticationProvider.isValidLong(verificationCode) || !totp.verify(verificationCode)) {
      throw new BadCredentialsException("Invalid verfication code");
    }

    final Authentication result = super.authenticate(auth);
    return new UsernamePasswordAuthenticationToken(persona, result.getCredentials(), result.getAuthorities());
  }

  private static boolean isValidLong(final String code) {
    try {
      Long.parseLong(code);
    } catch (final NumberFormatException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
