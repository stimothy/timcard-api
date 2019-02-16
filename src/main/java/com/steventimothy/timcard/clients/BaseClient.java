package com.steventimothy.timcard.clients;

import com.steventimothy.timcard.clients.config.ClientsConfig;
import com.steventimothy.timcard.schemas.ids.sessions.AdminSessionId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>The BaseClient Abstract Class</h1>
 * <p>This class holds the base logic for calling other system.</p>
 */
@Slf4j
@Component
public abstract class BaseClient {

  /**
   * The configuration class for the clients.
   */
  private ClientsConfig clientsConfig;
  /**
   * The environment of the server.
   */
  private Environment environment;
  /**
   * The rest caller.
   */
  protected RestTemplate restTemplate;

  /**
   * Gets the system session id.
   * @return the system session id.
   */
  protected AdminSessionId getSystemSessionId() {
    return new AdminSessionId(clientsConfig.getSystemSessionId());
  }

  /**
   * Gets the path to the PMS system.
   *
   * @return The PMS system path.
   */
  protected String getPmsPath() {
    return getBaseUrl() + "/pms";
  }

  /**
   * Gets the path to the UAS system.
   *
   * @return The UAS system path.
   */
  protected String getUasPath() {
    return getBaseUrl() + "/uas";
  }

  /**
   * Gets the base url.
   *
   * @return The base url.
   */
  private String getBaseUrl() {
    return getProtocol() + getAddress() + getPort();
  }

  /**
   * Gets the port.
   * @return The port.
   */
  private String getPort() {
    return ":" + environment.getProperty("local.server.port");
  }

  /**
   * Gets the server address.
   * @return The address.
   */
  private String getAddress() {
    return environment.getProperty("server.address");
  }

  /**
   * Gets the server protocol
   * @return The protocol.
   */
  private String getProtocol() {
    return "http://";
  }


  /**
   * The constructor.
   *
   * @param environment The environment of the system.
   * @param restTemplate The rest caller.
   * @param clientsConfig The configurations for the client classes.
   */
  public BaseClient(Environment environment, RestTemplate restTemplate, ClientsConfig clientsConfig) {
    this.environment = environment;
    this.restTemplate = restTemplate;
    this.clientsConfig = clientsConfig;
  }
}
