package com.steventimothy.timcard.schemas.permissions;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleTest extends PermissionsBaseComponent {

  /**
   * Tests that getRoles works as intended.
   */
  @Test
  public void testGetRoles() {
    Role role = Role.SUPER_ADMIN;

    assertThat(role.getRoles())
        .hasSize(1)
        .contains(Role.GENERAL);
  }

  /**
   * Tests that getPermissions works as intended.
   */
  @Test
  public void testGetPermissions() {
    Role role = Role.SUPER_ADMIN;

    assertThat(role.getPermissions())
        .hasSize(2)
        .contains(Permission.CREATE_USER, Permission.SUPER_ADMIN);
  }

  /**
   * Tests that hasPermission works as intended.
   */
  @Test
  public void testHasPermission() {
    Role role = Role.SUPER_ADMIN;

    assertThat(role.hasPermission(Permission.CREATE_USER))
        .isTrue();
  }

  /**
   * Tests the lombok.
   */
  @Test
  public void testRole() {
    EqualsVerifier.forClass(Role.class)
        .verify();
  }
}
