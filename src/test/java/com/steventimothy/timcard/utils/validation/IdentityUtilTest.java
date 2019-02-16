package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.schemas.exceptions.ForbiddenException;
import com.steventimothy.timcard.schemas.exceptions.UnauthorizedException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.Role;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class IdentityUtilTest extends ValidationBaseComponent {

  /**
   * Tests that you can get the sessionId from the identityUtil,
   * Given a good sessionId.
   */
  @Test
  public void testValidate_Valid() {
    SessionId sessionId = createLocalGeneralSessionId();

    //SessionId sessionId2 = super.identityUtil.validate(sessionId.getEncodedValue(), Collections.singletonList(Permission.CREATE_USER));

//    assertThat(sessionId2)
//        .isEqualTo(sessionId);
  }

  /**
   * Tests that a bad sessionId will throw an Unauthorized exception.
   */
  @Test(expected = UnauthorizedException.class)
  public void testValidate_Unauthorized() {
    //super.identityUtil.validate("hippo", Collections.singletonList(Permission.CREATE_USER));
  }

  /**
   * Tests that a good sessionId that doesn't have permissions will throw a Forbidden exception.
   */
  @Test(expected = ForbiddenException.class)
  public void testValidate_Forbidden() {
    SessionId sessionId = createLocalGeneralSessionId();
    //super.identityUtil.validate(sessionId.getEncodedValue(), Collections.singletonList(Permission.SUPER_ADMIN));
  }
}
