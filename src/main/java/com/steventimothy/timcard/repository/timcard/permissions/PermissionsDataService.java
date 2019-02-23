package com.steventimothy.timcard.repository.timcard.permissions;

import com.steventimothy.timcard.repository.schemas.DataPermission;
import com.steventimothy.timcard.repository.timcard.role_permissions.RolePermissionsDataService;
import com.steventimothy.timcard.repository.timcard.user_roles.UserRolesDataService;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.utils.mappers.PermissionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
   * The database layer to talk to the permissions table.
   */
  private PermissionsDbService permissionsDbService;
  /**
   * The data service layer to talk to the user_roles data table.
   */
  private UserRolesDataService userRolesDataService;
  /**
   * The data service layer to talk to the role_permissions data table.
   */
  private RolePermissionsDataService rolePermissionsDataService;
  /**
   * Utility to map permissions and names.
   */
  private PermissionMapper permissionMapper;

  /**
   * Creates a permission in the database.
   * @param permission The permission to insert.
   * @return The permission that was inserted.
   * @throws InvalidDataException Throws if the permission could not be created.
   * @throws DatabaseDataException Throws if the data used to query was bad.
   */
  public Permission createPermission(Permission permission)
      throws InvalidDataException, DatabaseDataException {

    DataPermission dataPermission = this.permissionsDbService.insert(permission.getValue());

    if (dataPermission != null) {
      return this.permissionMapper.map(dataPermission);
    }
    else {
      throw new InvalidDataException("The permission could not be created.");
    }
  }

  /**
   * Creates a permission in the database.
   * @param name The name of the permission to insert
   * @return The permission that was inserted.
   * @throws InvalidDataException Throws if the permission could not be created.
   * @throws DatabaseDataException Throws if the data used to query was bad.
   */
  public Permission createPermission(String name)
      throws InvalidDataException, DatabaseDataException {

    DataPermission dataPermission = this.permissionsDbService.insert(name);

    if (dataPermission != null) {
      return this.permissionMapper.map(dataPermission);
    }
    else {
      throw new InvalidDataException("The permission could not be created.");
    }
  }

  /**
   * Gets a permission by its id.
   * @param id The id of the permission.
   * @return The permission matching the id.
   * @throws InvalidDataException Throws if the permission could not be created.
   * @throws DatabaseDataException Throws if the data used to query was bad.
   */
  public Permission getPermission(Long id)
      throws InvalidDataException, DatabaseDataException {

    DataPermission dataPermission = this.permissionsDbService.get(id);

    if (dataPermission != null) {
      return this.permissionMapper.map(dataPermission);
    }
    else {
      throw new InvalidDataException("The permission could not be retrieved.");
    }
  }

  /**
   * Gets a permission id.
   * @param permission The permission to get.
   * @return The id of the permission.
   * @throws InvalidDataException Throws if the permission could not be created.
   * @throws DatabaseDataException Throws if the data used to query was bad.
   */
  public Long getPermissionId(Permission permission)
      throws InvalidDataException, DatabaseDataException {

    DataPermission dataPermission = this.permissionsDbService.get(permission.getValue());

    if (dataPermission != null) {
      return dataPermission.id();
    }
    else {
      throw new InvalidDataException("The permission could not be found.");
    }
  }

  /**
   * Gets a permission id.
   * @param name The name of the permission.
   * @return The id of the permission matching the name.
   * @throws InvalidDataException Throws if the permission could not be created.
   * @throws DatabaseDataException Throws if the data used to query was bad.
   */
  public Long getPermissionId(String name)
      throws InvalidDataException, DatabaseDataException {

    DataPermission dataPermission = this.permissionsDbService.get(name);

    if (dataPermission != null) {
      return dataPermission.id();
    }
    else {
      throw new InvalidDataException("The permission could not be found.");
    }
  }

  /**
   * Updates the permission to have the new name.
   * @param id The id of the permission.
   * @param newName The new name of the permission.
   * @throws InvalidDataException Throws if the data was bad.
   * @throws DatabaseDataException Throws if the data in the query was bad.
   */
  public void update(Long id, String newName)
      throws InvalidDataException, DatabaseDataException {

    DataPermission dataPermission = this.permissionsDbService.get(id);
    update(dataPermission, newName);
  }

  /**
   * Updates the permission to have the new name.
   * @param permission The permission to update.
   * @param newName The new name of the permission.
   * @throws InvalidDataException Throws if the data was bad.
   * @throws DatabaseDataException Throws if the data in the query was bad.
   */
  public void update(Permission permission, String newName)
      throws InvalidDataException, DatabaseDataException {

    DataPermission dataPermission = this.permissionsDbService.get(permission.getValue());
    update(dataPermission, newName);
  }

  /**
   * Updates the permission to have the new name.
   * @param oldName The old name of the permission.
   * @param newName The new name of the permission.
   * @throws InvalidDataException Throws if the data was bad.
   * @throws DatabaseDataException Throws if the data in the query was bad.
   */
  public void update(String oldName, String newName)
      throws InvalidDataException, DatabaseDataException {

    DataPermission dataPermission = this.permissionsDbService.get(oldName);
    update(dataPermission, newName);
  }

  /**
   * Deletes a permission in the database.
   * @param id The id of the permission to delete.
   * @throws InvalidDataException Throws if the data was bad.
   * @throws DatabaseDataException Throws if the data used in the query was bad.
   */
  public void delete(Long id)
      throws InvalidDataException, DatabaseDataException {

    if (!this.permissionsDbService.delete(id)) {
      throw new InvalidDataException("The permission could not be deleted.");
    }
  }

  /**
   * Deletes a permission in the databse.
   * @param permission The permission to delete.
   * @throws InvalidDataException Throws if the data was bad.
   * @throws DatabaseDataException Throws if the data used in the query was bad.
   */
  public void delete(Permission permission)
      throws InvalidDataException, DatabaseDataException {

    if (!this.permissionsDbService.delete(permission.getValue())) {
      throw new InvalidDataException("The permission could not be deleted.");
    }
  }

  /**
   * Deletes a permission in the databse.
   * @param name The name of the permission to delete.
   * @throws InvalidDataException Throws if the data was bad.
   * @throws DatabaseDataException Throws if the data used in the query was bad.
   */
  public void delete(String name)
      throws InvalidDataException, DatabaseDataException {

    if (!this.permissionsDbService.delete(name)) {
      throw new InvalidDataException("The permission could not be deleted.");
    }
  }

  /**
   * Gets a list of permissions that a user has.
   * @param userId The user id to get permissions.
   * @return The list of permissions the user has.
   * @throws InvalidDataException throws if the user id was not valid.
   * @throws DatabaseDataException throws if the data used to query the database was bad.
   */
  public List<Permission> getUserPermissions(UserId userId)
      throws InvalidDataException, DatabaseDataException {

    List<Long> roleIds = userRolesDataService.getRoleIds(userId);

    return roleIds.stream()
        .map(roleId -> rolePermissionsDataService.getPermissionIds(roleId))
        .flatMap(Collection::stream)
        .map(this::getPermission)
        .collect(Collectors.toList());
  }

  /**
   * Updates a permission in the database.
   * @param dataPermission The data permission to update.
   * @param newName The new name of the permission.
   * @throws InvalidDataException throws if the user id was not valid.
   * @throws DatabaseDataException throws if the data used to query the database was bad.
   */
  private void update(DataPermission dataPermission, String newName)
      throws InvalidDataException, DatabaseDataException {

    if (dataPermission != null) {
      dataPermission.name(newName);

      if (!this.permissionsDbService.update(dataPermission)) {
        throw new InvalidDataException("The permission could not be updated.");
      }
    }
    else {
      throw new InvalidDataException("The permission does not exist.");
    }
  }
}
