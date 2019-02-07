package com.steventimothy.timcard.schemas.permissions;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class PermissionTest extends PermissionsBaseComponent {

  /**
   * Tests the lombok.
   */
  @Test
  public void testPermission() {
    EqualsVerifier.forClass(Permission.class)
        .verify();
  }
}
