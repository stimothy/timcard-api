package com.steventimothy.timcard.repository.timcard.user_roles;

import com.steventimothy.timcard.repository.schemas.DataUserRole;
import com.steventimothy.timcard.repository.timcard.roles.RolesDataService;
import com.steventimothy.timcard.schemas.ids.users.UserId;
import com.steventimothy.timcard.schemas.permissions.Role;
import com.steventimothy.timcard.utils.mappers.IdMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>The UserRolesDataService Class</h1>
 * <p>This class is the service between the system and the
 * user_roles table in the database.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserRolesDataService {

  /**
   * The database layer to talk to the user_roles table.
   */
  private UserRolesDbService userRolesDbService;
  /**
   * The utility used for mapping ids.
   */
  private IdMapper idMapper;
  /**
   * The data service layer to talk to the roles data table.
   */
  private RolesDataService rolesDataService;

  /**
   * Gets the role ids of linked to the user.
   * @param userId The user id of the user.
   * @return The roles ids linked to the user.
   */
  public List<Long> getRoleIds(UserId userId) {
    return userRolesDbService.getAll(idMapper.mapUserIdToRawId(userId)).stream()
        .map(DataUserRole::role_id)
        .collect(Collectors.toList());
  }

  /**
   * Adds a role to a user.
   * @param userId The user id of the user to add the role to.
   * @param role The role to add to the user.
   */
  public void addRole(UserId userId, Role role) {
    Long role_id = rolesDataService.getRoleId(role.getValue());
    userRolesDbService.insert(idMapper.mapUserIdToRawId(userId), role_id);
  }
}
