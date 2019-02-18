package com.steventimothy.timcard.clients;

import com.steventimothy.timcard.schemas.exceptions.ForbiddenException;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.fail;

public class PmsClientTest extends ClientsBaseComponent {

  /**
   * Tests that checking permissions on a user with valid permissions does not
   * throw a forbidden exception.
   */
  @Test
  public void testCheckPermissions_Valid() {
    try {
      SessionId sessionId = getSuperAdminSessionId();
      checkPermissions(sessionId, Arrays.asList(
          Permission.SUPER_ADMIN,
          Permission.ADMIN,
          Permission.USER
      ));
    }
    catch (ForbiddenException ex) {
      fail("The test should not have thrown a forbidden exception.");
    }
  }

  /**
   * Tests that a user with invalid permissions throws a forbidden exception.
   */
  @Test(expected = ForbiddenException.class)
  public void testCheckPermissions_Invalid() {
    fail("This test cant be tested until a user can log in.");
  }
}
