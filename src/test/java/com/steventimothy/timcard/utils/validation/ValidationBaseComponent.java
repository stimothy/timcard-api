package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.utils.UtilsBaseComponent;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ValidationBaseComponent extends UtilsBaseComponent {

  /**
   * The utility used for identifying sessionIds and their permissions.
   */
  @Autowired
  protected IdentityUtil identityUtil;
//  /**
//   * The utility used for verifying correct data on a user.
//   */
//  @Autowired
//  protected UserUtil userUtil;
}
