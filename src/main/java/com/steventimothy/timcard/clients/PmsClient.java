package com.steventimothy.timcard.clients;

import com.steventimothy.timcard.clients.config.ClientsConfig;
import com.steventimothy.timcard.schemas.exceptions.ForbiddenException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.exceptions.UnauthorizedException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * <h1>The PmsClient Class</h1>
 * <p>This class holds the calls to the PMS endpoints.</p>
 */
@Slf4j
@Component
public class PmsClient extends BaseClient {

  /**
   * Checks to see if a user has permission.
   *
   * @param sessionId   The sessionId of the user.
   * @param permissions The permissions the user needs.
   * @throws UnauthorizedException throws if the user is unknown to the system.
   * @throws ForbiddenException throws if the user does not have the correct permissions.
   */
  public void checkPermissions(SessionId sessionId, List<Permission> permissions)
      throws UnauthorizedException, ForbiddenException {

    try {
      super.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getPmsPath() + "/admin/" + sessionId.getEncodedValue())
          .build().toUri())
          .header(HttpHeaders.AUTHORIZATION, getSystemSessionId().getEncodedValue())
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .body(permissions), String.class);
    }
    catch (HttpClientErrorException ex) {
      if (HttpStatus.UNAUTHORIZED.equals(ex.getStatusCode())) {
        throw new UnauthorizedException("User is unknown.");
      }
      else if (HttpStatus.FORBIDDEN.equals(ex.getStatusCode())) {
        throw new ForbiddenException("Permission denied.");
      }
      else {
        throw ex;
      }
    }
  }

  /**
   * Adds a role to the user.
   *
   * @param userId The user id of the user.
   * @param role   The role to add.
   * @throws InvalidDataException Throws if the user id was bad and couldn't add the role to it.
   */
  public void addRole(UserId userId, Role role)
      throws InvalidDataException {

    try {
      super.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getPmsPath() + "/admin/roles/" + userId.getEncodedValue())
          .build().toUri())
          .header(HttpHeaders.AUTHORIZATION, getSystemSessionId().getEncodedValue())
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .body(role), String.class);
    }
    catch (HttpClientErrorException ex) {
      if (HttpStatus.BAD_REQUEST.equals(ex.getStatusCode())) {
        throw new InvalidDataException("Could not add the role to the user id.");
      }
      else {
        throw ex;
      }
    }
  }


  /**
   * The Constructor.
   *
   * @param environment   The environment of the system.
   * @param restTemplate  The rest caller.
   * @param clientsConfig The configurations for the client classes.
   */
  @Autowired
  public PmsClient(Environment environment, RestTemplate restTemplate, ClientsConfig clientsConfig) {
    super(environment, restTemplate, clientsConfig);
  }
}
