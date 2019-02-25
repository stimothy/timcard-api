package com.steventimothy.timcard.repository.timcard.role_permissions;

import com.steventimothy.timcard.repository.schemas.DataRolePermission;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.permissions.RolePermission;
import com.steventimothy.timcard.utils.mappers.PermissionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>The RolePermissionsDataService Class</h1>
 * <p>This class is the data service class between the system
 * and the role_permissions database layer.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class RolePermissionsDataService {

//  /**
//   * The service layer used to talk to the role_permissions table.
//   */
//  private RolePermissionsDbService rolePermissionsDbService;
//  private PermissionMapper permissionMapper;
//
//  public RolePermission createRolePermission(RolePermission rolePermission)
//      throws InvalidDataException, DatabaseDataException {
//
//    return insertPermission(this.permissionMapper.map(rolePermission));
//  }
//
//  public RolePermission createRolePermission(Long roleId, Long permissionId)
//      throws InvalidDataException, DatabaseDataException {
//
//    DataRolePermission dataRolePermission = new DataRolePermission()
//        .role_id(roleId)
//        .permission_id(permissionId);
//
//    return insertPermission(new DataRolePermission()
//        .role_id(roleId)
//        .permission_id(permissionId)
//    );
//  }
//
//  public Long getRolePermissionId(RolePermission rolePermission)
//      throws InvalidDataException, DatabaseDataException {
//
//    DataRolePermission dataRolePermission = this.rolePermissionsDbService.get(this.permissionMapper.map(rolePermission));
//
//    if (dataRolePermission != null) {
//      return this.permissionMapper.map(dataRolePermission).id();
//    }
//    else {
//      throw new InvalidDataException("The role permission could not be retrieved.");
//    }
//  }
//
//  public RolePermission getRolePermission(Long id)
//      throws InvalidDataException, DatabaseDataException {
//
//    DataRolePermission dataRolePermission = this.rolePermissionsDbService.get(id);
//
//    if (dataRolePermission != null) {
//      return this.permissionMapper.map(dataRolePermission);
//    }
//    else {
//      throw new InvalidDataException("The role permission could not be retrieved.");
//    }
//  }
//
//  public List<RolePermission> getAllRolePermissionsByRoleId(Long roleId)
//      throws InvalidDataException, DatabaseDataException {
//
//    List<DataRolePermission> dataRolePermissions = this.rolePermissionsDbService.getAllByRoleId(roleId);
//
//    if (!dataRolePermissions.isEmpty()) {
//      return dataRolePermissions.stream()
//          .map(dataRolePermission -> this.permissionMapper.map(dataRolePermission))
//          .collect(Collectors.toList());
//    }
//    else {
//      throw new InvalidDataException("The role permission could not be retrieved.");
//    }
//  }
//
//  public RolePermission getAllRolePermissionsByPermissionId(Long permissionId)
//      throws InvalidDataException, DatabaseDataException {
//
//    List<DataRolePermission> dataRolePermissions = this.rolePermissionsDbService.getAllByPermissionId(permissionId);
//
//    if (!dataRolePermissions.isEmpty()) {
//      return dataRolePermissions.stream()
//          .map(dataRolePermission -> this.permissionMapper.map(dataRolePermission))
//          .collect(Collectors.toList());
//    }
//    else {
//      throw new InvalidDataException("The role permission could not be retrieved.");
//    }
//  }
//
//  public void updateRolePermission(RolePermission rolePermission) {
//
//    DataRolePermission dataRolePermission = this.permissionMapper.map(rolePermission);
//
//    if (!this.rolePermissionsDbService.update(dataRolePermission)) {
//      throw new InvalidDataException("The role permission could not be updated.");
//    }
//  }
//
////  public void deleteRolePermission()
//
//  private RolePermission insertPermission(DataRolePermission dataRolePermission)
//      throws InvalidDataException, DatabaseDataException {
//
//    dataRolePermission = this.rolePermissionsDbService.insert(dataRolePermission);
//
//    if (dataRolePermission != null) {
//      return this.permissionMapper.map(dataRolePermission);
//    }
//    else {
//      throw new InvalidDataException("The role permission could not be created.");
//    }
//  }
//
//
//
//
//
//
//
//
//
//
//
//
//  /**
//   * Gets the permission ids linked to a role.
//   * @param roleId The role id of the role to get permissions.
//   * @return The list of permissions linked to the role.
//   * @throws DatabaseDataException throws if the data passed to query the database was bad.
//   */
//  public List<Long> getPermissionIds(Long roleId)
//      throws DatabaseDataException {
//
//    return rolePermissionsDbService.getAllByRoleId(roleId).stream()
//        .map(DataRolePermission::permission_id)
//        .collect(Collectors.toList());
//  }
}
