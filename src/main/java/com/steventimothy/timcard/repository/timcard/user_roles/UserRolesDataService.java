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

  private UserRolesDbService userRolesDbService;
  private IdMapper idMapper;
  private RolesDataService rolesDataService;

  public List<Long> getRoleIds(UserId userId) {
    return userRolesDbService.getAll(idMapper.mapUserIdToRawId(userId)).stream()
        .map(DataUserRole::role_id)
        .collect(Collectors.toList());
  }

  public void addRole(UserId userId, Role role) {
    Long role_id = rolesDataService.getRoleId(role.getValue());
    userRolesDbService.insert(idMapper.mapUserIdToRawId(userId), role_id);
  }
}
