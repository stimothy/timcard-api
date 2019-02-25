package com.steventimothy.timcard.repository.timcard.roles;

import com.steventimothy.timcard.repository.schemas.DataRole;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.schemas.permissions.RoleType;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.utils.mappers.PermissionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * <h1>The RolesDataService Class</h1>
 * <p>This class is the data service layer between the system
 * and the roles table in the database.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class RolesDataService {

  /**
   * The service that talks to the roles table.
   */
  private RolesDbService rolesDbService;
  /**
   * The utility mapper that maps permissions.
   */
  private PermissionMapper permissionMapper;

  /**
   * Creates a role in the database.
   * @param role The role to create.
   * @return The role containing the id that was created.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public Role createRole(Role role)
      throws InvalidDataException {

    try {
      DataRole dataRole = this.permissionMapper.map(role);

      Long id = this.rolesDbService.insert(dataRole);

      return getRole(id);
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Gets a role from the database.
   * @param id The id of the role to get.
   * @return The role retrieved from the database.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public Role getRole(Long id)
      throws InvalidDataException {

    try {
      return this.permissionMapper.map(this.rolesDbService.get(id));
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Gets a role from the database.
   * @param roleType The type of role to retrieve.
   * @return The role retrieved from the database.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public Role getRole(RoleType roleType)
      throws InvalidDataException {

    try {
      return this.permissionMapper.map(this.rolesDbService.get(roleType.getName()));
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Gets the role id of a role type.
   * @param roleType The type of role to get an id for.
   * @return The id of the role type retrieved.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public Long getRoleId(RoleType roleType)
      throws InvalidDataException {

    return getRole(roleType).id();
  }

  /**
   * Get the role type of the id.
   * @param id The id of the role type to retrieve.
   * @return The role type retrieved.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public RoleType getRoleType(Long id)
      throws InvalidDataException {

    return getRole(id).roleType();
  }

  /**
   * Updates a role in the database.
   * @param role The role with the updated values.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public void updateRole(Role role)
      throws InvalidDataException {

    role.lastModified(Instant.now());

    try {
      if (!this.rolesDbService.update(this.permissionMapper.map(role))) {
        throw new InvalidDataException("The role to update could not be found.");
      }
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Deletes a role in the database.
   * @param id The id of the role to delete.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public void deleteRole(Long id)
      throws InvalidDataException {

    try {
      if (!this.rolesDbService.delete(id)) {
        throw new InvalidDataException("The role to delete could not be found.");
      }
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Deletes a role in the database.
   * @param roleType The role type to delete.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public void deleteRole(RoleType roleType)
      throws InvalidDataException {

    try {
      if (!this.rolesDbService.delete(roleType.getName())) {
        throw new InvalidDataException("The role to delete could not be found.");
      }
    }
    catch (DatabaseDataException ex) {
      throw new InvalidDataException("The data used to query the database was bad.");
    }
  }

  /**
   * Deletes a role in the database.
   * @param role The role to delete.
   * @throws InvalidDataException Throws if the data used to query the database was bad.
   */
  public void deleteRole(Role role)
      throws InvalidDataException {

    if (role.id() != null) {
      deleteRole(role.id());
    }
    else {
      deleteRole(role.roleType());
    }
  }


































//  /**
//   * The database layer for the roles table.
//   */
//  private RolesDbService rolesDbService;
//
//  /**
//   * Gets a role id by name.
//   *
//   * @param name The name of the role id.
//   * @return The id of the role.
//   * @throws DatabaseDataException throws if the data used in the query was bad.
//   */
//  public Long getRoleId(String name) throws DatabaseDataException {
//    return rolesDbService.get(name).id();
//  }
}
