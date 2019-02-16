package com.steventimothy.timcard.clients.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The ClientsConfig Class</h1>
 * <p>This class holds the properties for the clients.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "client.admin")
@PropertySource("classpath:db.properties")
@Component
public class ClientsConfig {

  /**
   * The rawId of the admin session id.
   */
  private String systemSessionId;
}
