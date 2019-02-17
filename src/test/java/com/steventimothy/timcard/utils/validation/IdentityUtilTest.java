package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.schemas.exceptions.ForbiddenException;
import com.steventimothy.timcard.schemas.exceptions.UnauthorizedException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class IdentityUtilTest extends ValidationBaseComponent {

  /**
   * Tests that a user with valid permissions will be validated.
   */
  @Test
  public void testValidateUserPermissions_Valid() {
    User user = createLocalUser();
    SessionId sessionId = getGeneralSessionId();

    requestCreateUser(user, sessionId);

    SessionId sessionId2 = super.identityUtil.validateUserPermissions(sessionId.getEncodedValue(), Collections.singletonList(Permission.CREATE_USER));

    assertThat(sessionId2)
        .isEqualTo(sessionId);
  }

  /**
   * Tests that a user with an invalid session id is unauthorized.
   */
  @Test(expected = UnauthorizedException.class)
  public void testValidateUserPermissions_Unauthorized() {
    super.identityUtil.validateUserPermissions("myBadSessionId", Collections.singletonList(Permission.CREATE_USER));
  }

  /**
   * Tests that a user without the proper permissions is invalid.
   */
  @Test(expected = ForbiddenException.class)
  public void testValidateUserPermissions_Forbidden() {
    fail("This test cannot be tested yet.");
  }
}
