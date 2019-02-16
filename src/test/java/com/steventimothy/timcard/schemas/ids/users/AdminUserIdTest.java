package com.steventimothy.timcard.schemas.ids.users;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminUserIdTest extends UsersBaseComponent {

  /**
   * Tests that the right data is returned from the getEncodedValue method.
   */
  @Test
  public void testAdminUserId_GetEncodedValue() {
    String rawId = "T-TTT";
    UserId id = createLocalAdminUserId(rawId);

    assertThat(id.getEncodedValue())
        .isNotNull()
        .startsWith("user.admin.")
        .endsWith(rawId);

    UserId id2 = createLocalGeneralUserId(null);

    assertThat(id2.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that the right data is returned from the toString method.
   */
  @Test
  public void testAdminUserId_ToString() {
    String rawId = "T-TTT";
    UserId id = createLocalAdminUserId(rawId);

    assertThat(id.toString())
        .isNotNull()
        .startsWith("AdminUserId(")
        .contains(id.getEncodedValue());

    UserId id2 = createLocalAdminUserId(null);

    assertThat(id2.toString())
        .isNotNull()
        .startsWith("AdminUserId(")
        .contains("null");
  }

  /**
   * Tests that the constructor works.
   */
  @Test
  public void testAdminUserId_Constructor() {
    UserId id = createLocalAdminUserId();

    assertThat(id)
        .isNotNull();

    UserId id2 = createLocalAdminUserId(null);

    assertThat(id2)
        .isNotNull();
  }
}
