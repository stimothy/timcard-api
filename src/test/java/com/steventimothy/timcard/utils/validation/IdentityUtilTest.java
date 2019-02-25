package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.schemas.exceptions.ForbiddenException;
import com.steventimothy.timcard.schemas.exceptions.UnauthorizedException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class IdentityUtilTest extends ValidationBaseComponent {

//  /**
//   * Tests that a user with valid permissions will be validated.
//   */
//  @Test
//  public void testValidateUserPermissions_Valid_General() {
//    SessionId sessionId = getSuperAdminSessionId();
//    SessionId sessionId2 = validateUserPermissions(sessionId.getEncodedValue(), Arrays.asList(
//        Permission.SUPER_ADMIN,
//        Permission.ADMIN,
//        Permission.USER)
//    );
//
//    assertThat(sessionId2)
//        .isEqualTo(sessionId);
//  }
//
//  /**
//   * Tests that a user with an invalid session id is unauthorized.
//   */
//  @Test(expected = UnauthorizedException.class)
//  public void testValidateUserPermissions_Unauthorized() {
//    validateUserPermissions("myBadSessionId", Collections.singletonList(Permission.PUBLIC));
//  }
//
//  /**
//   * Tests that a user without the proper permissions is invalid.
//   */
//  @Test(expected = ForbiddenException.class)
//  public void testValidateUserPermissions_Forbidden() {
//    fail("This test cannot be tested until a user can log in.");
//  }
}
