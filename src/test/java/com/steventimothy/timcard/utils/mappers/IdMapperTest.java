package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.schemas.ids.Id;
import com.steventimothy.timcard.schemas.ids.sessions.GeneralSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class IdMapperTest extends MappersBaseComponent {

  /**
   * Tests that mapping an encoded value to a general id is valid.
   */
  @Test
  public void testMapEncodedValueToSessionId_Valid_General() {
    SessionId sessionId = createLocalGeneralSessionId();
    SessionId sessionId2 = super.idMapper.mapEncodedValueToSessionId(sessionId.getEncodedValue());

    assertThat(sessionId2)
        .isEqualTo(sessionId);
  }

  /**
   * tests that mapping an encoded value to user id is valid.
   */
  @Test
  public void testMapEncodedValueToSessionId_Valid_User() {
    SessionId sessionId = createLocalUserSessionId();
    SessionId sessionId2 = super.idMapper.mapEncodedValueToSessionId(sessionId.getEncodedValue());

    assertThat(sessionId2)
        .isEqualTo(sessionId);
  }

  /**
   * Tests that mapping an encoded value admin id is valid.
   */
  @Test
  public void testMapEncodedValueToSessionId_Valid_Admin() {
    SessionId sessionId = createLocalAdminSessionId();
    SessionId sessionId2 = super.idMapper.mapEncodedValueToSessionId(sessionId.getEncodedValue());

    assertThat(sessionId2)
        .isEqualTo(sessionId);
  }

  /**
   * Tests that an invalid type on the encoded value throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapEncodedValueToSessionId_Invalid_SecondIdentifier() {
    super.idMapper.mapEncodedValueToSessionId("session.hippo." + UUID.randomUUID());
  }

  /**
   * Tests that an invalid value on the encoded value throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapEncodedValueToSessionId_Invalid_Value() {
    super.idMapper.mapEncodedValueToSessionId("session.admin.hippo");
  }

  /**
   * Tests that mapping an encoded value to a general id is valid.
   */
  @Test
  public void testMapEncodedValueToUserId_Valid_General() {
    UserId userId = createLocalGeneralUserId();
    UserId userId2 = super.idMapper.mapEncodedValueToUserId(userId.getEncodedValue());

    assertThat(userId2)
        .isEqualTo(userId);
  }

  /**
   * Tests that mapping an encoded value admin id is valid.
   */
  @Test
  public void testMapEncodedValueToUserId_Valid_Admin() {
    UserId userId = createLocalAdminUserId();
    UserId userId2 = super.idMapper.mapEncodedValueToUserId(userId.getEncodedValue());

    assertThat(userId2)
        .isEqualTo(userId);
  }

  /**
   * Tests that an invalid type on the encoded value throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapEncodedValueToUserId_Invalid_SecondIdentifier() {
    super.idMapper.mapEncodedValueToUserId("user.hippo." + 4L);
  }

  /**
   * Tests that an invalid value on the encoded value throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapEncodedValueToUserId_Invalid_Value() {
    super.idMapper.mapEncodedValueToUserId("session.admin.hippo");
  }

  /**
   * Tests that mapping an encoded value to a general id is valid.
   */
  @Test
  public void testMapEncodedValueToId_Valid_General() {
    SessionId sessionId = createLocalGeneralSessionId();
    Id id = super.idMapper.mapEncodedValueToId(sessionId.getEncodedValue());

    assertThat(id)
        .isInstanceOf(GeneralSessionId.class)
        .isEqualTo(sessionId);
  }

  /**
   * Tests that a null encoded id throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapEncodedValueToId_Invalid_Null() {
    super.idMapper.mapEncodedValueToId(null);
  }

  /**
   * Tests that the parsed encoded value with bad length throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapEncodedValueToId_Invalid_Length() {
    super.idMapper.mapEncodedValueToId("sessionadmin" + UUID.randomUUID());
  }

  /**
   * Tests that a bad identifier throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapEncodedValueToId_Invalid_FirstIdentifier() {
    super.idMapper.mapEncodedValueToId("hippo.admin." + UUID.randomUUID());
  }
}