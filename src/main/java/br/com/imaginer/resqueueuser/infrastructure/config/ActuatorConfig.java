package br.com.imaginer.resqueueuser.infrastructure.config;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ActuatorConfig {

  private static final String APP = "ResQueue User Auth";
  private static final String VERSION = "1.0.0";
  private static final String DESCRIPTION = "User Auth from ResQueue";

  @Bean
  public InfoContributor customInfoContributor() {
    return builder -> {
      builder.withDetail("app", Map.of("name", APP, "version", VERSION));
      builder.withDetail("description", DESCRIPTION);
    };
  }

}
