package br.com.imaginer.resqueueuser.infrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

  private final JwtProperties jwtProperties;

  public SecurityConfiguration(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

    http

        .csrf(ServerHttpSecurity.CsrfSpec::disable)

        .authorizeExchange(exchanges -> exchanges
            .pathMatchers("/eureka/**").permitAll()
            .pathMatchers("/actuator/**").permitAll()
            .pathMatchers("/users/create").permitAll()
            .pathMatchers("/auth/login/**").permitAll()
            .pathMatchers("/users/v3/**").permitAll()
            .pathMatchers("/swagger-ui/**").permitAll()
            .pathMatchers("/webjars/**").permitAll()
            .anyExchange().authenticated()

        ).oauth2ResourceServer(oauth2 -> oauth2
            .jwt(Customizer.withDefaults())
        );

    return http.build();
  }

  @Bean
  public ReactiveJwtDecoder jwtDecoder() {
    return ReactiveJwtDecoders.fromIssuerLocation(jwtProperties.getIssuerUri());
  }

}
