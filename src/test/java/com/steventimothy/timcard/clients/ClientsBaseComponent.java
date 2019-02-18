package com.steventimothy.timcard.clients;

import com.steventimothy.timcard.BaseComponent;
import com.steventimothy.timcard.schemas.ids.sessions.SessionId;
import com.steventimothy.timcard.schemas.permissions.Permission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class ClientsBaseComponent extends BaseComponent {

  /**
   * The client that talks to the PMS system.
   */
  @Autowired
  private PmsClient pmsClient;

  /**
   * Checks that the permissions of a user are valid.
   * @param sessionId The sessionId of the user.
   * @param permissions The permissions the user needs.
   */
  protected void checkPermissions(SessionId sessionId, List<Permission> permissions) {
    this.pmsClient.checkPermissions(sessionId, permissions);
  }
}
