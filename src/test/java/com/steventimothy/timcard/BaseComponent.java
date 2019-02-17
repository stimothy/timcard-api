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

//  /**
//   * The helper for creating a salt.
//   */
//  private static final String SALT_HELPER = "8gXDY3goXEcpy7EjitKyqTp2NU60w0a4/z/ue6wGz51ALWI6MWsOKUvgAdu0Mi+R3M53uDJwDvoo6F6BZ";
//  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//
//  @Autowired
//  private ClientsConfig clientsConfig;
//  private IdMapper idMapper;
  /**
   * Used to make rest calls.
   */
  @Autowired
  protected TestRestTemplate restTemplate;

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
//
//  /**
//   * Creates a local general session id.
//   *
//   * @return a general session Id.
//   */
//  protected SessionId createLocalGeneralSessionId() {
//    return createLocalGeneralSessionId(UUID.randomUUID().toString());
//  }
//
//  /**
//   * Creates a local general session id.
//   *
//   * @param rawId The given id.
//   * @return The general sessionId with the given id.
//   */
//  protected SessionId createLocalGeneralSessionId(String rawId) {
//    return new GeneralSessionId(rawId);
//  }
//
//  /**
//   * Creates a local user session id.
//   *
//   * @return a user session Id.
//   */
//  protected SessionId createLocalUserSessionId() {
//    return createLocalUserSessionId(UUID.randomUUID().toString());
//  }
//
//  /**
//   * Creates a local user session id.
//   *
//   * @param rawId The given id.
//   * @return The user sessionId with the given id.
//   */
//  protected SessionId createLocalUserSessionId(String rawId) {
//    return new UserSessionId(rawId);
//  }
//
//  /**
//   * Creates a local admin session id.
//   *
//   * @return a admin session Id.
//   */
//  protected SessionId createLocalAdminSessionId() {
//    return createLocalAdminSessionId(UUID.randomUUID().toString());
//  }
//
//  /**
//   * Creates a local admin session id.
//   *
//   * @param rawId The given id.
//   * @return The admin sessionId with the given id.
//   */
//  protected SessionId createLocalAdminSessionId(String rawId) {
//    return new AdminSessionId(rawId);
//  }
//
//  protected UserId createLocalGeneralUserId() {
//    return new GeneralUserId(createRandomUserIdString());
//  }
//
//  /**
//   * Creates a local general user id.
//   *
//   * @param rawId The given id.
//   * @return The general userId with the given id.
//   */
//  protected UserId createLocalGeneralUserId(String rawId) {
//    return new GeneralUserId(rawId);
//  }
//
//  /**
//   * Creates a local admin user id.
//   *
//   * @return a admin user Id.
//   */
//  protected UserId createLocalAdminUserId() {
//    return createLocalAdminUserId(createRandomUserIdString());
//  }
//
//  /**
//   * Creates a local admin user id.
//   *
//   * @param rawId The given id.
//   * @return The admin userId with the given id.
//   */
//  protected UserId createLocalAdminUserId(String rawId) {
//    return new AdminUserId(rawId);
//  }
//
//  protected User createLocalGeneralUser() {
//    String rawId = createRandomUserIdString();
//    return createLocalUser(new GeneralUserId(rawId), "testUser-" + rawId, "testUser-" + rawId +"@test.com", "ch33t@sRunFaSt");
//  }
//
//  protected User createLocalAdminUser() {
//    String rawId = createRandomUserIdString();
//    return createLocalUser(new AdminUserId(rawId), "testUser-" + rawId, "testUser-" + rawId +"@test.com", "anTsW@lk!na1ine");
//  }
//
//  protected User createLocalUser(UserId userId, String username, String email, String password) {
//    return new User()
//        .userId(userId)
//        .username(username)
//        .email(email)
//        .password(password);
//  }
//
//  protected SessionId getSystemSessionId() {
//    return new AdminSessionId(clientsConfig.getSystemSessionId());
//  }
//
//  /**
//   * Gets a general session id.
//   *
//   * @return The general session id.
//   */
//  protected SessionId getGeneralSessionId() {
//    ResponseEntity<String> responseEntity = requestGetGeneralSessionId();
//
//    assertStatus(responseEntity, HttpStatus.OK);
//    assertThat(responseEntity.getBody())
//        .isNotNull();
//
//    return this.idMapper.mapEncodedValueToSessionId(responseEntity.getBody());
//  }
//
//  /**
//   * Gets a general session id.
//   *
//   * @return The response to the rest call.
//   */
//  protected ResponseEntity<String> requestGetGeneralSessionId() {
//    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getUasHost())
//        .build().toUri())
//        .build(), String.class);
//  }
//
//  protected UserId createAdminUser() {
//
//  }
//
////  /**
////   * Used to encrypt passwords
////   */
////  private static StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
////
////  /**
////   * Sets up the class variables.
////   */
////  @BeforeClass
////  public static void beforeClass() {
////    strongPasswordEncryptor = new StrongPasswordEncryptor();
////  }
////
////  /**
////   * Creates a local user.
////   *
////   * @return A local user.
////   */
////  protected User createLocalUser() {
////    return createLocalUser(createLocalGeneralUserId(), "testUser4", "testUser4@test.com", "ch33t@sRunFaSt");
////  }
////
////  /**
////   * Creates a local user.
////   *
////   * @return a local user.
////   */
////  protected User createAltLocalUser() {
////    return createLocalUser(createAltLocalGeneralUserId(), "testUser5", "testUser5@test.com", "anTsW@lk!na1ine");
////  }
////
////  /**
////   * Creates a local user.
////   *
////   * @param userId   The userId of the user.
////   * @param username The username of th euser.
////   * @param email    The email of the user.
////   * @param password The password of the user.
////   * @return The newly created user.
////   */
////  protected User createLocalUser(UserId userId, String username, String email, String password) {
////    return new User()
////        .userId(userId)
////        .username(username)
////        .email(email)
////        .password(encryptPassword(password));
////  }
//
//  /**
//   * Gets the salt needed to hash the password.
//   * @param username The username of the user's password to hash.
//   * @return The byte array salt for hashing the password.
//   */
//  protected byte[] getSalt(String username) {
//    if (username.length() > 4) {
//      String salt = SALT_HELPER + username.substring(0, 5) + "==";
//      return Base64.getDecoder().decode(salt);
//    }
//    else {
//      throw new IllegalArgumentException("Username must be at least 5 characters long.");
//    }
//  }
//
//  /**
//   * hashes a password.
//   * @param password the password to hash.
//   * @param salt the salt used to hash the password.
//   * @return The encrypted password.
//   */
//  protected String hashPassword(String password, byte[] salt) {
//    try {
//      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
//      PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, 1000, 512);
//      SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
//      byte[] bytes = secretKey.getEncoded();
//      return Base64.getEncoder().encodeToString(bytes);
//    }
//    catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//      throw new IllegalStateException("The password could not be hashed.", e);
//    }
//  }
//
//
//
//  private String createRandomUserIdString() {
//    SecureRandom secureRandom = new SecureRandom();
//    StringBuilder stringBuilder = new StringBuilder();
//    stringBuilder.append(ALPHABET.charAt(Math.abs(secureRandom.nextInt()) % ALPHABET.length()))
//        .append('-');
//
//    for (int i = 0; i < 3; ++i) {
//      stringBuilder.append(ALPHABET.charAt(Math.abs(secureRandom.nextInt()) % ALPHABET.length()));
//    }
//
//    return stringBuilder.toString();
//  }
//
//
//
//
//
//
//
//
//
//
//
//
//
////  public byte[] hashPassword(String password) {
////    SecureRandom random = new SecureRandom();
////    byte[] salt = new byte[64];
////    random.nextBytes(salt);
////    try {
////      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
////      PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1000, 512);
////      SecretKey key = skf.generateSecret(spec);
////      byte[] res = key.getEncoded();
////      return res;
////    }
////    catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
////      //Noop.
////      return null;
////    }
////  }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////  public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
////    SecureRandom random = new SecureRandom();
////    byte bytes[] = new byte[20];
////    random.nextBytes(bytes);
////
////    try {
////      SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
////      PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
////      SecretKey key = skf.generateSecret( spec );
////      byte[] res = key.getEncoded( );
////      return res;
////
////    } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
////      throw new RuntimeException( e );
////    }
////  }
}
