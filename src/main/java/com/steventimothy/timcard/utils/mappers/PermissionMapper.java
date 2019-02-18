package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.repository.schemas.DataPermission;
import com.steventimothy.timcard.schemas.permissions.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The PermissionMapper Class</h1>
 * <p>This class is responsible for mapping an a permission or role name to Enum.</p>
 */
@Slf4j
@Component
public class PermissionMapper {

  public Permission map(DataPermission dataPermission) {
    switch (dataPermission.name()) {
      case "admin":
        return Permission.ADMIN;
      case "public":
        return Permission.PUBLIC;
      case "super-admin":
        return Permission.SUPER_ADMIN;
      case "user":
        return Permission.USER;
      default:
        throw new UnsupportedOperationException("Permission mapping not supported yet.");
    }
  }
}
