package de.kodestruktor.amazon.stash.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests().antMatchers("/").permitAll();
  }
}