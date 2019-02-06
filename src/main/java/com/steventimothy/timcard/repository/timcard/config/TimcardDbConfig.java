package com.steventimothy.timcard.repository.timcard.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The TimcardDbConfig</h1>
 * <p>This class holds the properties for the timcard database.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db.timcard")
@PropertySource("classpath:db.properties")
@Component
public class TimcardDbConfig {

  /**
   * The database name value.
   */
  private String databaseName;
}
