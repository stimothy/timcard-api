package com.steventimothy.timcard.utils.validation;

import com.steventimothy.timcard.schemas.ids.users.GeneralUserId;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserUtilTest extends ValidationBaseComponent {

//  /**
//   * Tests that a user has valid data.
//   */
//  @Test
//  public void testValidateUserCreation_Valid() {
//    User user = createLocalUser();
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is valid with a null raw id.
//   */
//  @Test
//  public void testValidateUserCreation_Valid_NullRawId() {
//    User user = createLocalUser();
//    user.userId(new GeneralUserId());
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is valid with a test id lower range.
//   */
//  @Test
//  public void testValidateUserCreation_Valid_TestIdLow() {
//    User user = createLocalUser();
//    user.userId(new GeneralUserId(1L));
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is valid with a test id higher range.
//   */
//  @Test
//  public void testValidateUserCreation_Valid_TestIdHigh() {
//    User user = createLocalUser();
//    user.userId(new GeneralUserId(100L));
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is valid with a username length low range.
//   */
//  @Test
//  public void testValidateUserCreation_Valid_UsernameLow() {
//    User user = createLocalUser();
//    user.username("12345");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is valid with a username length high range.
//   */
//  @Test
//  public void testValidateUserCreation_Valid_UsernameHigh() {
//    User user = createLocalUser();
//    user.username("12345678901234567890123456789012345678901234567890");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is valid with an email length high range.
//   */
//  @Test
//  public void testValidateUserCreation_Valid_EmailHigh() {
//    User user = createLocalUser();
//    user.email("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901@test.com");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is valid with an email matching regex.
//   */
//  @Test
//  public void testValidateUserCreation_Valid_EmailRegex() {
//    User user = createLocalUser();
//    user.email("test.test.test@test.com");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is valid with a right password length.
//   */
//  @Test
//  public void testValidateUserCreation_Valid_PasswordLength() {
//    User user = createLocalUser();
//    user.password(encryptPassword("myPassword"));
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isTrue();
//  }
//
//  /**
//   * Tests that a user is invalid due to null user.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_NullUser() {
//    assertThat(super.userUtil.validateUserCreation(null))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid due to null userId wrapper.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_NullUserId() {
//    User user = createLocalUser();
//    user.userId(null);
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with not a test id lower range.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_NotTestIdLow() {
//    User user = createLocalUser();
//    user.userId(new GeneralUserId(0L));
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with not a test id higher range.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_NotTestIdHigh() {
//    User user = createLocalUser();
//    user.userId(new GeneralUserId(101L));
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with a null username.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_NullUsername() {
//    User user = createLocalUser();
//    user.username(null);
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with a username length low range.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_UsernameLow() {
//    User user = createLocalUser();
//    user.username("1234");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with a username length high range.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_UsernameHigh() {
//    User user = createLocalUser();
//    user.username("123456789012345678901234567890123456789012345678901");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with a null email.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_NullEmail() {
//    User user = createLocalUser();
//    user.email(null);
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with an email length high range.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_EmailHigh() {
//    User user = createLocalUser();
//    user.email("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012@test.com");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with an email matching regex.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_EmailRegex() {
//    User user = createLocalUser();
//    user.email("1234123414321");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with a null password.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_NullPassword() {
//    User user = createLocalUser();
//    user.password(null);
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
//
//  /**
//   * Tests that a user is invalid with a not right password length.
//   */
//  @Test
//  public void testValidateUserCreation_Invalid_PasswordLength() {
//    User user = createLocalUser();
//    user.password("123");
//
//    assertThat(super.userUtil.validateUserCreation(user))
//        .isFalse();
//  }
}
