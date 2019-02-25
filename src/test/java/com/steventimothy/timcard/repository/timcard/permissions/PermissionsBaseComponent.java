package com.steventimothy.timcard.repository.timcard.permissions;

import com.steventimothy.timcard.repository.schemas.DataPermission;
import com.steventimothy.timcard.repository.timcard.TimcardBaseComponent;
import com.steventimothy.timcard.schemas.Permission;
import com.steventimothy.timcard.schemas.PermissionType;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PermissionsBaseComponent extends TimcardBaseComponent {

  /**
   * The service that talks to the permissions table.
   */
  @Autowired
  private PermissionsDbService permissionsDbService;
  /**
   * The data service that talks to the database layer.
   */
  @Autowired
  private PermissionsDataService permissionsDataService;

  /**
   * Sets up the data for each method.
   */
  @Before
  public void before() {
    for (PermissionType permissionType : PermissionType.values()) {
      try {
        deletePermission(permissionType);
      }
      catch (InvalidDataException ex) {
        //Noop.
      }
    }
  }

  /**
   * Creates a local data permission.
   * @return The local data permission created.
   */
  protected DataPermission createLocalDataPermission() {
    return createLocalDataPermission(PermissionType.PUBLIC.getName());
  }

  /**
   * Creates an alternate local data permission.
   * @return The data permission created.
   */
  protected DataPermission createAltLocalDataPermission() {
    return createLocalDataPermission(PermissionType.USER.getName());
  }

  /**
   * Creates a local data permission.
   * @param name The name of the local data permission to create.
   * @return The data permission created.
   */
  protected DataPermission createLocalDataPermission(String name) {
    return new DataPermission()
        .name(name);
  }

  /**
   * Creates a local permission.
   * @return The local permission created.
   */
  protected Permission createLocalPermission() {
    return createLocalPermission(PermissionType.PUBLIC);
  }

  /**
   * Creates an alternate local permission.
   * @return The local permission created.
   */
  protected Permission createAltLocalPermission() {
    return createLocalPermission(PermissionType.USER);
  }

  /**
   * Creates a local permission.
   * @param permissionType The permission type to create.
   * @return The local permission created.
   */
  protected Permission createLocalPermission(PermissionType permissionType) {
    return new Permission()
        .permissionType(permissionType);
  }

  /**
   * Inserts a data permission into the database.
   * @param dataPermission The data permission to insert.
   * @return The id of the inserted data permission.
   */
  protected Long insert(DataPermission dataPermission) {
    return this.permissionsDbService.insert(dataPermission);
  }

  /**
   * Gets the data permission from the database.
   * @param id The id of the data permission to retrieve.
   * @return The data permission retrieved.
   */
  protected DataPermission get(Long id) {
    return this.permissionsDbService.get(id);
  }

  /**
   * Gets the data permission from the database.
   * @param name The name of the data permission to retrieve.
   * @return The data permission retrieved.
   */
  protected DataPermission get(String name) {
    return this.permissionsDbService.get(name);
  }

  /**
   * Updates a data permission in the database.
   * @param dataPermission The database with the new data to update.
   * @return True if it was successful.
   */
  protected Boolean update(DataPermission dataPermission) {
    return this.permissionsDbService.update(dataPermission);
  }

  /**
   * Deletes a data permission in the database.
   * @param id The id of the data permission to delete.
   * @return True if the data permission was deleted.
   */
  protected Boolean delete(Long id) {
    return this.permissionsDbService.delete(id);
  }

  /**
   * Deletes a data permission in the database.
   * @param name The name of the data permission to delete.
   * @return True if the data permission was deleted.
   */
  protected Boolean delete(String name) {
    return this.permissionsDbService.delete(name);
  }

  /**
   * Create a permission in the database.
   * @param permission The permission to create.
   * @return The permission created with the id.
   */
  protected Permission createPermission(Permission permission) {
    return this.permissionsDataService.createPermission(permission);
  }

  /**
   * Gets a permission from the database.
   * @param id The id of the permission to retrieve.
   * @return The permission retrieved.
   */
  protected Permission getPermission(Long id) {
    return this.permissionsDataService.getPermission(id);
  }

  /**
   * Gets a permission from the database.
   * @param permissionType The type of permission to retrieve.
   * @return The permission retrieved.
   */
  protected Permission getPermission(PermissionType permissionType) {
    return this.permissionsDataService.getPermission(permissionType);
  }

  /**
   * Gets the permission id from the database.
   * @param permissionType The type of permission to retrieve the id.
   * @return The id of the permission type.
   */
  protected Long getPermissionId(PermissionType permissionType) {
    return this.permissionsDataService.getPermissionId(permissionType);
  }

  /**
   * Gets the permission type from the database.
   * @param id The id of the permission type to retrieve.
   * @return The permission type retrieved.
   */
  protected PermissionType getPermissionType(Long id) {
    return this.permissionsDataService.getPermissionType(id);
  }

  /**
   * Updates a permission in the database.
   * @param permission The permission with updated values.
   */
  protected void updatePermission(Permission permission) {
    this.permissionsDataService.updatePermission(permission);
  }

  /**
   * Deletes a permission from the database.
   * @param id The id of the permission to delete.
   */
  protected void deletePermission(Long id) {
    this.permissionsDataService.deletePermission(id);
  }

  /**
   * Deletes a permission from the database.
   * @param permissionType The permission type of the permission to delete.
   */
  protected void deletePermission(PermissionType permissionType) {
    this.permissionsDataService.deletePermission(permissionType);
  }

  /**
   * Deletes a permission from the database.
   * @param permission The permission to delete.
   */
  protected void deletePermission(Permission permission) {
    this.permissionsDataService.deletePermission(permission);
  }
}
