package com.steventimothy.timcard.repository.timcard.user_ids.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserIdsDbConfig Class</h1>
 * <p>This class holds the properties of the user_ids table.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db.timcard.user-ids")
@PropertySource("classpath:db.properties")
@Component
public class UserIdsDbConfig {

  /**
   * The table name value.
   */
  private String tableName;
}
