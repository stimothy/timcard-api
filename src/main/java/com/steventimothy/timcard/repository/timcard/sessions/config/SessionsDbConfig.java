package com.steventimothy.timcard.repository.timcard.sessions.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The SessionsDbConfig Class</h1>
 * <p>This class holds the properties of the sessions table.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db.timcard.sessions")
@PropertySource("classpath:db.properties")
@Component
public class SessionsDbConfig {

  /**
   * The table name value.
   */
  private String tableName;
}
