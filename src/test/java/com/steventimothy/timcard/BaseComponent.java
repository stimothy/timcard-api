package com.steventimothy.timcard;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseComponent {

  /**
   * Used to make rest calls.
   */
  @Autowired
  protected TestRestTemplate restTemplate;

  /**
   * Asserts that the response from a rest call has the given status.
   *
   * @param responseEntity The response of the rest call.
   * @param status         The expected status.
   */
  protected void assertStatus(ResponseEntity responseEntity, HttpStatus status) {
    assertThat(responseEntity.getStatusCode())
        .isEqualTo(status);
  }

  /**
   * Gets the AMS base URL.
   *
   * @return The AMS URL.
   */
  protected String getAmsHost() {
    return "/ams";
  }

  /**
   * Gets the PMS base URL.
   *
   * @return The PMS URL.
   */
  protected String getPmsHost() {
    return "/pms";
  }

  /**
   * Gets the UAS base URL.
   *
   * @return The UAS URL.
   */
  protected String getUasHost() {
    return "/uas";
  }
}
