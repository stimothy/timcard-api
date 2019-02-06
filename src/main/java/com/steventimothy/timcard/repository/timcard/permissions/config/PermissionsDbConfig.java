package com.steventimothy.timcard.repository.timcard.permissions.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The PermissionsDbConfig Class</h1>
 * <p>This class holds the properties of the permissions table.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db.timcard.permissions")
@PropertySource("classpath:db.properties")
@Component
public class PermissionsDbConfig {

  /**
   * The table name value.
   */
  private String tableName;
}
