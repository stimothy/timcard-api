package com.steventimothy.timcard.repository.timcard.roles.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The RolesDbConfig Class</h1>
 * <p>This class holds the properties of the roles table.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db.timcard.roles")
@PropertySource("classpath:db.properties")
@Component
public class RolesDbConfig {

  /**
   * The table name value.
   */
  private String tableName;
}
