package com.steventimothy.timcard.repository.timcard.permissions;

import com.steventimothy.timcard.repository.schemas.DataPermission;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.schemas.permissions.PermissionType;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.utils.mappers.PermissionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * <h1>The PermissionsDataService Class</h1>
 * <p>This class is the data service layer between the system and
 * the database.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class PermissionsDataService {

  /**
   * The service that talks to the permissions table.
   */
  private PermissionsDbService permissionsDbService;
  /**
   * The utility mapper that maps permissions.
   */
  private PermissionMapper permissionMapper;

  /**
   * Creates a permission in the database.
   * @param permission The permission to create.
   * @return The permission containing the id that was created.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public Permission createPermission(Permission permission)
      throws InvalidDataException {

    try {
      DataPermission dataPermission = this.permissionMapper.map(permission);

      Long id = this.permissionsDbService.insert(dataPermission);

      return getPermission(id);
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Gets a permission from the database.
   * @param id The id of the permission to get.
   * @return The permission retrieved from the database.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public Permission getPermission(Long id)
      throws InvalidDataException {

    try {
      return this.permissionMapper.map(this.permissionsDbService.get(id));
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Gets a permission from the database.
   * @param permissionType The type of permission to retrieve.
   * @return The permission retrieved from the database.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public Permission getPermission(PermissionType permissionType)
      throws InvalidDataException {

    try {
      return this.permissionMapper.map(this.permissionsDbService.get(permissionType.getName()));
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Gets the permission id of a permission type.
   * @param permissionType The type of permission to get an id for.
   * @return The id of the permission type retrieved.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public Long getPermissionId(PermissionType permissionType)
      throws InvalidDataException {

    return getPermission(permissionType).id();
  }

  /**
   * Get the permission type of the id.
   * @param id The id of the permission type to retrieve.
   * @return The permission type retrieved.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public PermissionType getPermissionType(Long id)
      throws InvalidDataException {

    return getPermission(id).permissionType();
  }

  /**
   * Updates a permission in the database.
   * @param permission The permission with the updated values.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public void updatePermission(Permission permission)
      throws InvalidDataException {

    permission.lastModified(Instant.now());

    try {
      if (!this.permissionsDbService.update(this.permissionMapper.map(permission))) {
        throw new InvalidDataException("The permission to update could not be found.");
      }
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Deletes a permission in the database.
   * @param id The id of the permission to delete.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public void deletePermission(Long id)
      throws InvalidDataException {

    try {
      if (!this.permissionsDbService.delete(id)) {
        throw new InvalidDataException("The permission to delete could not be found.");
      }
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Deletes a permission in the database.
   * @param permissionType The permission type to delete.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public void deletePermission(PermissionType permissionType)
      throws InvalidDataException {

    try {
      if (!this.permissionsDbService.delete(permissionType.getName())) {
        throw new InvalidDataException("The permission to delete could not be found.");
      }
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Deletes a permission in the database.
   * @param permission The permission to delete.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public void deletePermission(Permission permission)
      throws InvalidDataException {

    if (permission.id() != null) {
      deletePermission(permission.id());
    }
    else {
      deletePermission(permission.permissionType());
    }
  }





















//
////  /**
////   * Gets a list of permissions that a user has.
////   * @param userId The user id to get permissions.
////   * @return The list of permissions the user has.
////   * @throws InvalidDataException throws if the user id was not valid.
////   * @throws DatabaseDataException throws if the data used to query the database was bad.
////   */
////  public List<Permission> getUserPermissions(UserId userId)
////      throws InvalidDataException, DatabaseDataException {
////
////    List<Long> roleIds = userRolesDataService.getRoleIds(userId);
////
////    return roleIds.stream()
////        .map(roleId -> rolePermissionsDataService.getPermissionIds(roleId))
////        .flatMap(Collection::stream)
////        .map(this::getPermission)
////        .collect(Collectors.toList());
////  }
}
