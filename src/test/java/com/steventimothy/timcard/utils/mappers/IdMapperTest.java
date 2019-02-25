package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.ids.sessions.GeneralSessionId;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class IdMapperTest extends MappersBaseComponent {

//  /**
//   * Tests that a encoded value can be mapped to a sessionId.
//   */
//  @Test
//  public void testMapEncodedValueToSessionId_Valid() {
//    SessionId sessionId = createLocalGeneralSessionId();
//    SessionId sessionId2 = mapEncodedValueToSessionId(sessionId.getEncodedValue());
//
//    assertThat(sessionId2)
//        .isEqualTo(sessionId);
//  }
//
//  /**
//   * Tests that an invalid encoded Id throws an InvalidDataException if mapping to sessionId.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapEncodedValueToSessionId_Invalid() {
//    mapEncodedValueToSessionId("myBadSessionId");
//  }
//
//  /**
//   * Tests that an encoded valud can be mapped to a userId.
//   */
//  @Test
//  public void testMapEncodedValueToUserId_Valid() {
//    UserId userId = createLocalGeneralUserId();
//    UserId userId2 = mapEncodedValueToUserId(userId.getEncodedValue());
//
//    assertThat(userId2)
//        .isEqualTo(userId);
//  }
//
//  /**
//   * Tests that an invalid encoded Id throws an InvalidDataException if mapping to userId.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapEncodedValueToUserId_Invalid() {
//    mapEncodedValueToUserId("myBadUserId");
//  }
//
//  /**
//   * Tests that an encoded value can be mapped to an Id.
//   */
//  @Test
//  public void testMapEncodedValueToId_Valid() {
//    SessionId sessionId = createLocalGeneralSessionId();
//    SessionId sessionId2 = (SessionId) mapEncodedValueToId(sessionId.getEncodedValue());
//
//    assertThat(sessionId2)
//        .isEqualTo(sessionId);
//  }
//
//  /**
//   * Tests that an invalid encoded Id throws an InvalidDataException if mapping to id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapEncodedValueToId_Invalid_NullEncodedValue() {
//    mapEncodedValueToId(null);
//  }
//
//  /**
//   * Tests that an invalid encoded Id throws an InvalidDataException if mapping to id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapEncodedValueToId_Invalid_EncodedIdTooManyParts() {
//    mapEncodedValueToId("session.general.123.bad");
//  }
//
//  /**
//   * Tests that an invalid encoded Id throws an InvalidDataException if mapping to id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapEncodedValueToId_Invalid_BadStartOfEncodedId() {
//    mapEncodedValueToId("badFirstSection.general.123");
//  }
//
//  /**
//   * Tests that an invalid encoded Id throws an InvalidDataException if mapping to id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapEncodedValueToId_Invalid_BadSecondSection_SessionId() {
//    mapEncodedValueToId("session.badId.123");
//  }
//
//  /**
//   * Tests that an invalid encoded Id throws an InvalidDataException if mapping to id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapEncodedValueToId_Invalid_BadSecondSection_UserId() {
//    mapEncodedValueToId("user.badId.123");
//  }
//
//  /**
//   * Tests that a sessionId can be mapped to a rawId.
//   */
//  @Test
//  public void testMapSessionIdToRawId_Valid() {
//    String rawId = UUID.randomUUID().toString();
//    SessionId sessionId = createLocalGeneralSessionId(rawId);
//
//    String rawId2 = mapSessionIdToRawId(sessionId);
//
//    assertThat(rawId)
//        .isEqualTo(rawId2);
//  }
//
//  /**
//   * Tests that an invalid sessionId throws an InvalidDataException when mapped to a raw id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapSessionIdToRawId_Invalid() {
//    mapSessionIdToRawId(null);
//  }
//
//  /**
//   * Tests that a userId can be mapped to a raw id.
//   */
//  @Test
//  public void testMapUserIdToRawId_Valid() {
//    String rawId = createRandomRawUserId();
//    UserId userId = createLocalGeneralUserId(rawId);
//
//    String rawId2 = mapUserIdToRawId(userId);
//
//    assertThat(rawId2)
//        .isEqualTo(rawId);
//  }
//
//  /**
//   * Tests that an invalid userId throws an InvalidDataException when mapped to a raw id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapUserIdToRawId_Invalid() {
//    mapUserIdToRawId(null);
//  }
//
//  /**
//   * Tests that an id can be mapped to a raw id.
//   */
//  @Test
//  public void testMapIdToRawId_Valid() {
//    String rawId = UUID.randomUUID().toString();
//    SessionId sessionId = createLocalGeneralSessionId(rawId);
//
//    String rawId2 = mapIdToRawId(sessionId);
//
//    assertThat(rawId2)
//        .isEqualTo(rawId);
//  }
//
//  /**
//   * Tests that an invalid id throws an InvalidDataException when mapped to a raw id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapIdToRawId_Invalid_IdNull() {
//    mapIdToRawId(null);
//  }
//
//  /**
//   * Tests that an invalid id throws an InvalidDataException when mapped to a raw id.
//   */
//  @Test(expected = InvalidDataException.class)
//  public void testMapIdToRawId_Invalid_EncodedValueNull() {
//    mapIdToRawId(new GeneralSessionId());
//  }
}
