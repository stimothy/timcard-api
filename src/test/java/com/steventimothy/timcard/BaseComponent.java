package com.steventimothy.timcard;

import com.steventimothy.timcard.clients.config.ClientsConfig;
import com.steventimothy.timcard.schemas.ids.sessions.AdminSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.GeneralSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.AdminUserId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseComponent {

  /**
   * The letters of the alphabet for producing random user ids.
   */
  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  /**
   * The salt helper for creating a semi unique salt.
   */
  private static final String SALT_HELPER = "8gXDY3goXEcpy7EjitKyqTp2NU60w0a4/z/ue6wGz51ALWI6MWsOKUvgAdu0Mi+R3M53uDJwDvoo6F6BZ";
  private static SecureRandom secureRandom;

  @BeforeClass
  public static void setup() {
    secureRandom = new SecureRandom();
  }

  /**
   * Used to make rest calls.
   */
  @Autowired
  protected TestRestTemplate restTemplate;
  /**
   * The config for obtaining the system sessionId.
   */
  @Autowired
  protected ClientsConfig clientsConfig;

  /**
   * Creates a local general session id.
   * @return A local general session id.
   */
  protected GeneralSessionId createLocalGeneralSessionId() {
    return createLocalGeneralSessionId(UUID.randomUUID());
  }

  /**
   * Creates a local general session id.
   * @param uuid The raw value of the id.
   * @return The general session id created.
   */
  protected GeneralSessionId createLocalGeneralSessionId(UUID uuid) {
    return createLocalGeneralSessionId(uuid.toString());
  }

  /**
   * Creates a local general session id.
   * @param rawId The raw id of the session id.
   * @return The general session id created.
   */
  protected GeneralSessionId createLocalGeneralSessionId(String rawId) {
    return new GeneralSessionId(rawId);
  }

  /**
   * Creates a random local user.
   *
   * @return The user created.
   */
  protected User createLocalUser() {
    String rawId = createRandomRawUserId();
    return createLocalUser(new GeneralUserId(rawId), "testUser-" + rawId, "testUser-" + rawId + "@test.com", "ch33t@sRunFaSt");
  }

  /**
   * Creates a random local user with no Id.
   *
   * @return The user created.
   */
  protected User createLocalUserNoId() {
    String username = (createRandomRawUserId() + createRandomRawUserId()).replaceAll("-", "");
    String email = username + "@test.com";
    return createLocalUser(null, username, email, "ch33t@sRunFaSt");
  }

  /**
   * Creates a random local admin user.
   *
   * @return The user created.
   */
  protected User createLocalAdminUser() {
    String rawId = createRandomRawUserId();
    return createLocalUser(new AdminUserId(rawId), "testUser-" + rawId, "testUser-" + rawId + "@test.com", "anTsW@lk!na1ine");
  }

  /**
   * Creates a local user.
   *
   * @param userId   The user id of the user.
   * @param username The username of the user.
   * @param email    The email of the user.
   * @param password The password of the user.
   * @return The user created.
   */
  protected User createLocalUser(UserId userId, String username, String email, String password) {
    return new User()
        .userId(userId)
        .username(username)
        .email(email)
        .password(password);
  }

  /**
   * Gets the system super admin sessionId.
   * @return A super admin sessionId.
   */
  protected SessionId getSuperAdminSessionId() {
    return new AdminSessionId(clientsConfig.getSystemSessionId());
  }

  /**
   * Gets a general session id to use in the system.
   *
   * @return The general session id obtained from the system.
   */
  protected SessionId getGeneralSessionId() {
    ResponseEntity<SessionId> responseEntity = requestGetGeneralSessionId();

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().getEncodedValue())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Gets a general session id from the system.
   *
   * @return The response from the rest call.
   */
  protected ResponseEntity<SessionId> requestGetGeneralSessionId() {
    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getUasHost())
        .build().toUri())
        .build(), SessionId.class);
  }

  /**
   * Creates a random user in the database.
   *
   * @return The user id of the created user.
   */
  protected UserId createUser() {
    return createUser(createLocalUser());
  }

  /**
   * Creates a user in the database.
   *
   * @param user The user to create.
   * @return The userId of the created user.
   */
  protected UserId createUser(User user) {
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().getEncodedValue())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Creates a user in the database.
   *
   * @param user The user to create.
   * @return The response to the rest endpoint.
   */
  protected ResponseEntity<UserId> requestCreateUser(User user) {
    return requestCreateUser(user, getGeneralSessionId());
  }

  /**
   * Creates a user in the database.
   *
   * @param user      The user to create.
   * @param sessionId The sessionId to use for authorization.
   * @return The response to the rest endpoint.
   */
  protected ResponseEntity<UserId> requestCreateUser(User user, SessionId sessionId) {
    user.email(user.email().toLowerCase());
    user.password(hashPassword(user.password(), getSalt(user.username())));
    //TODO: Delete user before creating it.

    return this.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsHost())
        .build().toUri())
        .header(HttpHeaders.AUTHORIZATION, sessionId.getEncodedValue())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(user), UserId.class);
  }

  /**
   * Gets the salt needed to hash the password.
   *
   * @param username The username of the user's password to hash.
   * @return The byte array salt for hashing the password.
   */
  protected byte[] getSalt(String username) {
    String salt = SALT_HELPER;
    if (username == null || username.length() < 5) {
      salt += "abcde==";
    }
    else {
      salt += username.substring(0, 5) + "==";
      return Base64.getDecoder().decode(salt);
    }

    return Base64.getDecoder().decode(salt);
  }

  /**
   * hashes a password.
   *
   * @param password the password to hash.
   * @param salt     the salt used to hash the password.
   * @return The hashed password.
   */
  protected String hashPassword(String password, byte[] salt) {
    try {
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
      PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, 100, 512);
      SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
      byte[] bytes = secretKey.getEncoded();
      return Base64.getEncoder().encodeToString(bytes);
    }
    catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new IllegalStateException("The password could not be hashed.", e);
    }
  }

  /**
   * Creates a random raw id for user ids.
   *
   * @return The raw id created.
   */
  protected String createRandomRawUserId() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(getRandomCapitalAlphabet())
        .append('-');
    for (int i = 0; i < 3; ++i) {
      stringBuilder.append(getRandomCapitalAlphabet());
    }

    return stringBuilder.toString();
  }

  protected String createRandomName() {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < 25; i++) {
      stringBuilder.append(getRandomAlphabet());
    }

    return stringBuilder.toString();
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

  private char getRandomAlphabet() {
    return ALPHABET.charAt(Math.abs(secureRandom.nextInt()) % ALPHABET.length());
  }

  private char getRandomCapitalAlphabet() {
    return ALPHABET.charAt(Math.abs(secureRandom.nextInt()) % (ALPHABET.length() / 2));
  }
}
