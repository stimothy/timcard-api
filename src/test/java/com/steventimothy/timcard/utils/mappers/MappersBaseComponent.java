package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.utils.UtilsBaseComponent;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MappersBaseComponent extends UtilsBaseComponent {

  /**
   * Maps encoded values to ids.
   */
  @Autowired
  protected IdMapper idMapper;
}
