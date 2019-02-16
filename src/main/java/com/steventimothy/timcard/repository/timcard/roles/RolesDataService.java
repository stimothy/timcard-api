package com.steventimothy.timcard.repository.timcard.roles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
   * The database layer for the roles table.
   */
  private RolesDbService rolesDbService;

  /**
   * Gets a role id by name.
   * @param name The name of the role id.
   * @return The id of the role.
   */
  public Long getRoleId(String name) {
    return rolesDbService.get(name).id();
  }
}
