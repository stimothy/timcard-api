package com.steventimothy.timcard.repository.timcard.permissions;

import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.PermissionType;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class PermissionsDataServiceTest extends PermissionsBaseComponent {

  /**
   * Tests that a permission can be created in the database.
   */
  @Test
  public void testCreatePermission_Valid() {
    Permission permission = createLocalPermission();

    Permission permission2 = createPermission(permission);

    assertThat(permission2)
        .isNotNull()
        .isEqualToComparingOnlyGivenFields(permission, "permissionType");
    assertThat(permission2.id())
        .isNotNull();
  }

  /**
   * Tests that invalid data is thrown when creating a permission that already exists.
   */
  @Test(expected = InvalidDataException.class)
  public void testCreatePermission_Invalid() {
    createPermission(createLocalPermission());
    createPermission(createLocalPermission());
  }

  /**
   * Tests that a permission can be retrieved from the database.
   */
  @Test
  public void testGetPermission_Id_Valid() {
    Permission permission = createPermission(createLocalPermission());

    Permission permission2 = getPermission(permission.id());

    assertThat(permission2)
        .isEqualTo(permission);
  }

  /**
   * Tests that null is returned when permission not found.
   */
  @Test
  public void testGetPermission_Id_NotFound() {
    assertThat(getPermission(0L))
        .isNull();
  }

  /**
   * Tests that a permission can be retrieved from the database.
   */
  @Test
  public void testGetPermission_PermissionType_Valid() {
    Permission permission = createPermission(createLocalPermission());

    Permission permission2 = getPermission(permission.permissionType());

    assertThat(permission2)
        .isEqualTo(permission);
  }

  /**
   * Tests that null is returned if permission is not found.
   */
  @Test
  public void testGetPermission_PermissionType_Invalid() {
    assertThat(getPermission(PermissionType.PUBLIC))
        .isNull();
  }

  /**
   * Tests that a permission id can be retrieved from the database.
   */
  @Test
  public void testGetPermissionId() {
    Permission permission = createPermission(createLocalPermission());

    Long id = getPermissionId(permission.permissionType());

    assertThat(id)
        .isEqualTo(permission.id());
  }

  /**
   * Tests that a permission type can be retrieved from the database.
   */
  @Test
  public void testGetPermissionType() {
    Permission permission = createPermission(createLocalPermission());

    PermissionType permissionType = getPermissionType(permission.id());

    assertThat(permissionType)
        .isEqualTo(permission.permissionType());
  }

  /**
   * Tests that a permission can be updated in the database.
   */
  @Test
  public void testUpdatePermission_Valid() {
    Permission permission = createPermission(createLocalPermission());
    Instant instant = permission.lastModified();
    permission.permissionType(PermissionType.USER);
    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      //Noop.
    }

    updatePermission(permission);

    Permission permission2 = getPermission(permission.id());

    assertThat(permission2)
        .isEqualToIgnoringGivenFields(permission, "lastModified");
    assertThat(permission2.lastModified())
        .isAfter(instant);
  }

  /**
   * Tests that an InvalidDataException is thrown when a the changed permission type
   * is already in the database.
   */
  @Test(expected = InvalidDataException.class)
  public void testUpdatePermission_Invalid() {
    Permission permission = createPermission(createLocalPermission());
    Permission permission2 = createPermission(createAltLocalPermission());
    permission2.permissionType(permission.permissionType());

    updatePermission(permission2);
  }

  /**
   * Tests that a permission can be deleted from the database.
   */
  @Test
  public void testDeletePermission_Id_Valid() {
    Permission permission = createPermission(createLocalPermission());

    deletePermission(permission.id());

    assertThat(getPermission(permission.id()))
        .isNull();
  }

  /**
   * Tests that an InvalidDataException is thrown if the permission to be deleted is not found.
   */
  @Test(expected = InvalidDataException.class)
  public void testDeletePermission_Id_Invalid() {
    deletePermission(0L);
  }

  /**
   * Tests that a permission can be deleted from the database.
   */
  @Test
  public void testDeletePermission_PermissionType_Valid() {
    Permission permission = createPermission(createLocalPermission());

    deletePermission(permission.permissionType());

    assertThat(getPermission(permission.permissionType()))
        .isNull();
  }

  /**
   * Tests that an InvalidDataException is thrown if the permission type is not found
   * in the database.
   */
  @Test(expected = InvalidDataException.class)
  public void testDeletePermission_PermissionType_Invalid() {
    deletePermission(PermissionType.PUBLIC);
  }

  /**
   * Tests that a permission can be deleted from the database.
   */
  @Test
  public void testDeletePermission_Permission() {
    Permission permission = createPermission(createLocalPermission());

    deletePermission(permission);

    assertThat(getPermission(permission.id()))
        .isNull();
  }
}
