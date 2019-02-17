package com.steventimothy.timcard.clients;

import com.steventimothy.timcard.clients.config.ClientsConfig;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * <h1>The UasClient Class</h1>
 * <p>This class holds the calls to the UAS endpoints.</p>
 */
@Slf4j
@Component
public class UasClient extends BaseClient {

  public UserId getNewUserId() {
    return super.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getUasPath() + "/admin")
        .build().toUri())
        .header(HttpHeaders.AUTHORIZATION, getSystemSessionId().getEncodedValue())
        .accept(MediaType.APPLICATION_JSON)
        .build(), UserId.class).getBody();
  }

  public void freeUserId(UserId userId) {
    super.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getUasPath() + "/admin")
        .build().toUri())
        .header(HttpHeaders.AUTHORIZATION, getSystemSessionId().getEncodedValue())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(userId), String.class);
  }
















//
//  public UserId getUserIdFromSession(SessionId sessionId) {
//    try {
//      String id = super.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getUasPath() + "/" + sessionId.getEncodedValue())
//          .build().toUri())
//          .accept(MediaType.APPLICATION_JSON)
//          .build(), String.class).getBody();
//
//      return new GeneralUserId(id);
//    }
//    catch (HttpClientErrorException ex) {
//      if (HttpStatus.BAD_REQUEST.equals(ex.getStatusCode())) {
//        return null;
//      }
//      else {
//        throw ex;
//      }
//    }
//  }


  /**
   * The Constructor.
   *
   * @param environment  The environment of the system.
   * @param restTemplate The rest caller.
   * @param clientsConfig The configurations for the client classes.
   */
  @Autowired
  public UasClient(Environment environment, RestTemplate restTemplate, ClientsConfig clientsConfig) {
    super(environment, restTemplate, clientsConfig);
  }
}
