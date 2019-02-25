package com.steventimothy.timcard.repository.timcard.roles;

import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.schemas.permissions.RoleType;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class RolesDataServiceTest extends RolesBaseComponent {

  /**
   * Tests that a role can be created in the database.
   */
  @Test
  public void testCreateRole_Valid() {
    Role role = createLocalRole();

    Role role2 = createRole(role);

    assertThat(role2)
        .isNotNull()
        .isEqualToComparingOnlyGivenFields(role, "roleType");
    assertThat(role2.id())
        .isNotNull();
  }

  /**
   * Tests that invalid data is thrown when creating a role that already exists.
   */
  @Test(expected = InvalidDataException.class)
  public void testCreateRole_Invalid() {
    createRole(createLocalRole());
    createRole(createLocalRole());
  }

  /**
   * Tests that a role can be retrieved from the database.
   */
  @Test
  public void testGetRole_Id_Valid() {
    Role role = createRole(createLocalRole());

    Role role2 = getRole(role.id());

    assertThat(role2)
        .isEqualTo(role);
  }

  /**
   * Tests that null is returned when role not found.
   */
  @Test
  public void testGetRole_Id_NotFound() {
    assertThat(getRole(0L))
        .isNull();
  }

  /**
   * Tests that a role can be retrieved from the database.
   */
  @Test
  public void testGetRole_RoleType_Valid() {
    Role role = createRole(createLocalRole());

    Role role2 = getRole(role.roleType());

    assertThat(role2)
        .isEqualTo(role);
  }

  /**
   * Tests that null is returned if role is not found.
   */
  @Test
  public void testGetRole_RoleType_Invalid() {
    assertThat(getRole(RoleType.GENERAL))
        .isNull();
  }

  /**
   * Tests that a role id can be retrieved from the database.
   */
  @Test
  public void testGetRoleId() {
    Role role = createRole(createLocalRole());

    Long id = getRoleId(role.roleType());

    assertThat(id)
        .isEqualTo(role.id());
  }

  /**
   * Tests that a role type can be retrieved from the database.
   */
  @Test
  public void testGetRoleType() {
    Role role = createRole(createLocalRole());

    RoleType roleType = getRoleType(role.id());

    assertThat(roleType)
        .isEqualTo(role.roleType());
  }

  /**
   * Tests that a role can be updated in the database.
   */
  @Test
  public void testUpdateRole_Valid() {
    Role role = createRole(createLocalRole());
    Instant instant = role.lastModified();
    role.roleType(RoleType.USER);
    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      //Noop.
    }

    updateRole(role);

    Role role2 = getRole(role.id());

    assertThat(role2)
        .isEqualToIgnoringGivenFields(role, "lastModified");
    assertThat(role2.lastModified())
        .isAfter(instant);
  }

  /**
   * Tests that an InvalidDataException is thrown when a the changed role type
   * is already in the database.
   */
  @Test(expected = InvalidDataException.class)
  public void testUpdateRole_Invalid() {
    Role role = createRole(createLocalRole());
    Role role2 = createRole(createAltLocalRole());
    role2.roleType(role.roleType());

    updateRole(role2);
  }

  /**
   * Tests that a role can be deleted from the database.
   */
  @Test
  public void testDeleteRole_Id_Valid() {
    Role role = createRole(createLocalRole());

    deleteRole(role.id());

    assertThat(getRole(role.id()))
        .isNull();
  }

  /**
   * Tests that an InvalidDataException is thrown if the role to be deleted is not found.
   */
  @Test(expected = InvalidDataException.class)
  public void testDeleteRole_Id_Invalid() {
    deleteRole(0L);
  }

  /**
   * Tests that a role can be deleted from the database.
   */
  @Test
  public void testDeleteRole_RoleType_Valid() {
    Role role = createRole(createLocalRole());

    deleteRole(role.roleType());

    assertThat(getRole(role.roleType()))
        .isNull();
  }

  /**
   * Tests that an InvalidDataException is thrown if the role type is not found
   * in the database.
   */
  @Test(expected = InvalidDataException.class)
  public void testDeleteRole_RoleType_Invalid() {
    deleteRole(RoleType.GENERAL);
  }

  /**
   * Tests that a role can be deleted from the database.
   */
  @Test
  public void testDeleteRole_Role() {
    Role role = createRole(createLocalRole());

    deleteRole(role);

    assertThat(getRole(role.id()))
        .isNull();
  }
}
