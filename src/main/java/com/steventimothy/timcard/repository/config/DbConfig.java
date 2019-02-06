package com.steventimothy.timcard.repository.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The DbConfig Class</h1>
 * <p>This class holds the properties for the dbms.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db")
@PropertySource("classpath:db.properties")
@Component
public class DbConfig {

  /**
   * The host value.
   */
  private String host;
  /**
   * The user value.
   */
  private String user;
  /**
   * The password value.
   */
  private String password;
}
