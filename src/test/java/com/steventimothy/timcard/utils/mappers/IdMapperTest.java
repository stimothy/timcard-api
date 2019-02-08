package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.schemas.ids.Id;
import com.steventimothy.timcard.schemas.ids.sessions.*;
import com.steventimothy.timcard.schemas.ids.users.AdminUserId;
import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.ids.users.UserIdType;
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

  /**
   * Tests that a session id can be mapped to a raw id.
   */
  @Test
  public void testMapSessionIdToRawId_Valid() {
    UUID uuid = UUID.randomUUID();
    SessionId sessionId = new GeneralSessionId(uuid);

    UUID uuid2 = super.idMapper.mapSessionIdToRawId(sessionId);

    assertThat(uuid2)
        .isNotNull()
        .isEqualTo(uuid);
  }

  /**
   * Tests that a bad sessionId throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapSessionIdToRawId_Invalid() {
    super.idMapper.mapSessionIdToRawId(null);
  }

  /**
   * Tests that a user id can be mapped to a raw id.
   */
  @Test
  public void testMapUserIdToRawId_Valid() {
    Long rawId = 1L;
    UserId userId = new GeneralUserId(rawId);

    Long rawId2 = super.idMapper.mapUserIdToRawId(userId);

    assertThat(rawId2)
        .isNotNull()
        .isEqualTo(rawId);
  }

  /**
   * Tests that a bad userId throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapUserIdToRawId_Invalid() {
    super.idMapper.mapUserIdToRawId(null);
  }

  /**
   * Tests that mapping id to raw id is invalid due to null id.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapIdToRawId_Invalid_NullId() {
    super.idMapper.mapIdToRawId(null);
  }

  /**
   * Tests that mapping id to raw id is invalid due to null encoded value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapIdToRawId_Invalid_NullEncodedValue() {
    UserId userId = new GeneralUserId();
    super.idMapper.mapIdToRawId(userId);
  }

  /**
   * Tests that the idMapper can map a string raw id of session id to a sessionId.
   */
  @Test
  public void testMapRawIdToSessionId_String_Valid() {
    UUID uuid = UUID.randomUUID();
    SessionId sessionId = super.idMapper.mapRawIdToSessionId(uuid.toString(), SessionIdType.GENERAL);

    assertThat(sessionId.getEncodedValue())
        .contains(uuid.toString());

    UUID uuid2 = super.idMapper.mapSessionIdToRawId(sessionId);

    assertThat(uuid2)
        .isEqualTo(uuid);
  }

  /**
   * Tests that the idMapper can map a uuid raw id of session id to a sessionId.
   */
  @Test
  public void testMapRawIdToSessionId_UUID_Valid() {
    UUID uuid = UUID.randomUUID();
    SessionId sessionId = super.idMapper.mapRawIdToSessionId(uuid, SessionIdType.GENERAL);

    assertThat(sessionId.getEncodedValue())
        .contains(uuid.toString());

    UUID uuid2 = super.idMapper.mapSessionIdToRawId(sessionId);

    assertThat(uuid2)
        .isEqualTo(uuid);
  }

  /**
   * Tests that the idMapper can map a uuid raw id of session id to a sessionId.
   */
  @Test
  public void testMapRawIdToUserIdId_Valid() {
    Long rawId = 1L;
    UserId userId = super.idMapper.mapRawIdToUserId(rawId, UserIdType.GENERAL);

    assertThat(userId.getEncodedValue())
        .contains(rawId.toString());

    Long rawId2 = super.idMapper.mapUserIdToRawId(userId);

    assertThat(rawId2)
        .isEqualTo(rawId);
  }

  /**
   * Tests that a raw id can be mapped to a generalId.
   */
  @Test
  public void testMapRawIdToSessionId_Valid_GeneralId() {
    UUID uuid = UUID.randomUUID();
    SessionId sessionId = super.idMapper.mapRawIdToSessionId(uuid, SessionIdType.GENERAL);

    assertThat(sessionId)
        .isInstanceOf(GeneralSessionId.class);
  }

  /**
   * Tests that a raw id can be mapped to a userId.
   */
  @Test
  public void testMapRawIdToSessionId_Valid_UserId() {
    UUID uuid = UUID.randomUUID();
    SessionId sessionId = super.idMapper.mapRawIdToSessionId(uuid, SessionIdType.USER);

    assertThat(sessionId)
        .isInstanceOf(UserSessionId.class);
  }

  /**
   * Tests that a raw id can be mapped to a adminId.
   */
  @Test
  public void testMapRawIdToSessionId_Valid_AdminId() {
    UUID uuid = UUID.randomUUID();
    SessionId sessionId = super.idMapper.mapRawIdToSessionId(uuid, SessionIdType.ADMIN);

    assertThat(sessionId)
        .isInstanceOf(AdminSessionId.class);
  }

  /**
   * Tests that a raw id can be mapped to a general user id.
   */
  @Test
  public void testMapRawIdToUserId_Valid_General() {
    Long rawId = 1L;
    UserId userId = super.idMapper.mapRawIdToUserId(rawId, UserIdType.GENERAL);

    assertThat(userId)
        .isInstanceOf(GeneralUserId.class);
  }

  /**
   * Tests that a raw id can be mapped to an admin user id.
   */
  @Test
  public void testMapRawIdToUserId_Valid_Admin() {
    Long rawId = 1L;
    UserId userId = super.idMapper.mapRawIdToUserId(rawId, UserIdType.ADMIN);

    assertThat(userId)
        .isInstanceOf(AdminUserId.class);
  }

  /**
   * Tests that mapping a raw id to id will throw exception due to invalid uuid string.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapRawIdToSessionId_String_Invalid() {
    super.idMapper.mapRawIdToSessionId("hippo", SessionIdType.GENERAL);
  }

  /**
   * Tests that a null raw id throws exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapRawIdToSessionId_String_Null() {
    super.idMapper.mapRawIdToSessionId((String) null, SessionIdType.GENERAL);
  }

  /**
   * Tests that a null raw id throws exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapRawIdToSessionId_Uuid_Null() {
    super.idMapper.mapRawIdToSessionId((UUID) null, SessionIdType.GENERAL);
  }

  /**
   * Tests that a null raw id throws exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMapRawIdToUserId_Null() {
    super.idMapper.mapRawIdToUserId(null, UserIdType.GENERAL);
  }
}
