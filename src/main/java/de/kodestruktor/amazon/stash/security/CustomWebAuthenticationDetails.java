package de.kodestruktor.amazon.stash.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Christoph Wende
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

  private static final long serialVersionUID = 1L;

  private final String verificationCode;

  public CustomWebAuthenticationDetails(final HttpServletRequest request) {
    super(request);
    this.verificationCode = request.getParameter("code");
  }

  public String getVerificationCode() {
    return this.verificationCode;
  }
}