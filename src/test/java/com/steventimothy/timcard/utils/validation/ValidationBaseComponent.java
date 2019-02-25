package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import com.steventimothy.timcard.utils.UtilsBaseComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class ValidationBaseComponent extends UtilsBaseComponent {

//  /**
//   * The utility used for validating permissions of a user.
//   */
//  @Autowired
//  private IdentityUtil identityUtil;
//
//  /**
//   * Validates that a user has certain permissions.
//   * @param encodedValue The encoded value of the session of the user.
//   * @param permissions The list of permissions the user needs.
//   * @return The sessionId of the user if everything was validated.
//   */
//  protected SessionId validateUserPermissions(String encodedValue, List<Permission> permissions) {
//    return this.identityUtil.validateUserPermissions(encodedValue, permissions);
//  }
}
