package com.steventimothy.timcard.repository.timcard.roles;

import com.steventimothy.timcard.repository.schemas.DataRole;
import com.steventimothy.timcard.repository.timcard.TimcardBaseComponent;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.schemas.permissions.RoleType;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RolesBaseComponent extends TimcardBaseComponent {

  /**
   * The service that talks to the roles table.
   */
  @Autowired
  private RolesDbService rolesDbService;
  /**
   * The data service that talks to the database layer.
   */
  @Autowired
  private RolesDataService rolesDataService;

  /**
   * Sets up the data for each method.
   */
  @Before
  public void before() {
    for (RoleType roleType : RoleType.values()) {
      try {
        deleteRole(roleType);
      }
      catch (InvalidDataException ex) {
        //Noop.
      }
    }
  }

  /**
   * Creates a local data role.
   * @return The local data role created.
   */
  protected DataRole createLocalDataRole() {
    return createLocalDataRole(RoleType.GENERAL.getName());
  }

  /**
   * Creates an alternate local data role.
   * @return The data role created.
   */
  protected DataRole createAltLocalDataRole() {
    return createLocalDataRole(RoleType.USER.getName());
  }

  /**
   * Creates a local data role.
   * @param name The name of the local data role to create.
   * @return The data role created.
   */
  protected DataRole createLocalDataRole(String name) {
    return new DataRole()
        .name(name);
  }

  /**
   * Creates a local role.
   * @return The local role created.
   */
  protected Role createLocalRole() {
    return createLocalRole(RoleType.GENERAL);
  }

  /**
   * Creates an alternate local role.
   * @return The local role created.
   */
  protected Role createAltLocalRole() {
    return createLocalRole(RoleType.USER);
  }

  /**
   * Creates a local role.
   * @param roleType The role type to create.
   * @return The local role created.
   */
  protected Role createLocalRole(RoleType roleType) {
    return new Role()
        .roleType(roleType);
  }

  /**
   * Inserts a data role into the database.
   * @param dataRole The data role to insert.
   * @return The id of the inserted data role.
   */
  protected Long insert(DataRole dataRole) {
    return this.rolesDbService.insert(dataRole);
  }

  /**
   * Gets the data role from the database.
   * @param id The id of the data role to retrieve.
   * @return The data role retrieved.
   */
  protected DataRole get(Long id) {
    return this.rolesDbService.get(id);
  }

  /**
   * Gets the data role from the database.
   * @param name The name of the data role to retrieve.
   * @return The data role retrieved.
   */
  protected DataRole get(String name) {
    return this.rolesDbService.get(name);
  }

  /**
   * Updates a data role in the database.
   * @param dataRole The database with the new data to update.
   * @return True if it was successful.
   */
  protected Boolean update(DataRole dataRole) {
    return this.rolesDbService.update(dataRole);
  }

  /**
   * Deletes a data role in the database.
   * @param id The id of the data role to delete.
   * @return True if the data role was deleted.
   */
  protected Boolean delete(Long id) {
    return this.rolesDbService.delete(id);
  }

  /**
   * Deletes a data role in the database.
   * @param name The name of the data role to delete.
   * @return True if the data role was deleted.
   */
  protected Boolean delete(String name) {
    return this.rolesDbService.delete(name);
  }

  /**
   * Create a role in the database.
   * @param role The role to create.
   * @return The role created with the id.
   */
  protected Role createRole(Role role) {
    return this.rolesDataService.createRole(role);
  }

  /**
   * Gets a role from the database.
   * @param id The id of the role to retrieve.
   * @return The role retrieved.
   */
  protected Role getRole(Long id) {
    return this.rolesDataService.getRole(id);
  }

  /**
   * Gets a role from the database.
   * @param roleType The type of role to retrieve.
   * @return The role retrieved.
   */
  protected Role getRole(RoleType roleType) {
    return this.rolesDataService.getRole(roleType);
  }

  /**
   * Gets the role id from the database.
   * @param roleType The type of role to retrieve the id.
   * @return The id of the role type.
   */
  protected Long getRoleId(RoleType roleType) {
    return this.rolesDataService.getRoleId(roleType);
  }

  /**
   * Gets the role type from the database.
   * @param id The id of the role type to retrieve.
   * @return The role type retrieved.
   */
  protected RoleType getRoleType(Long id) {
    return this.rolesDataService.getRoleType(id);
  }

  /**
   * Updates a role in the database.
   * @param role The role with updated values.
   */
  protected void updateRole(Role role) {
    this.rolesDataService.updateRole(role);
  }

  /**
   * Deletes a role from the database.
   * @param id The id of the role to delete.
   */
  protected void deleteRole(Long id) {
    this.rolesDataService.deleteRole(id);
  }

  /**
   * Deletes a role from the database.
   * @param roleType The role type of the role to delete.
   */
  protected void deleteRole(RoleType roleType) {
    this.rolesDataService.deleteRole(roleType);
  }

  /**
   * Deletes a role from the database.
   * @param role The role to delete.
   */
  protected void deleteRole(Role role) {
    this.rolesDataService.deleteRole(role);
  }
}
