package com.steventimothy.timcard.clients;

import com.steventimothy.timcard.clients.config.ClientsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>The PmsClient Class</h1>
 * <p>This class holds the calls to the PMS endpoints.</p>
 */
@Slf4j
@Component
public class AmsClient extends BaseClient {

  /**
   * The Constructor.
   *
   * @param environment   The environment of the system.
   * @param restTemplate  The rest caller.
   * @param clientsConfig The configurations for the client classes.
   */
  @Autowired
  public AmsClient(Environment environment, RestTemplate restTemplate, ClientsConfig clientsConfig) {
    super(environment, restTemplate, clientsConfig);
  }
}
