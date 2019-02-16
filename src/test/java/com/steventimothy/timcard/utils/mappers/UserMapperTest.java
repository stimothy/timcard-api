package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.repository.schemas.DataUser;
import com.steventimothy.timcard.schemas.ids.users.UserIdType;
import com.steventimothy.timcard.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest extends MappersBaseComponent {

//  /**
//   * Tests that a user can be mapped to a dataUser.
//   */
//  @Test
//  public void testMapUserToDataUser() {
//    User user = createLocalUser();
//    DataUser dataUser = super.userMapper.map(user);
//
//    assertThat(dataUser)
//        .isEqualToComparingOnlyGivenFields(user, "username", "email");
//    assertThat(dataUser.id())
//        .isEqualTo(super.idMapper.mapUserIdToRawId(user.userId()));
//    assertThat(dataUser.enc_password())
//        .isEqualTo(user.password());
//  }
//
//  @Test
//  public void testMapDataUserToUser() {
//    DataUser dataUser = createLocalDataUser();
//    User user = super.userMapper.map(dataUser, UserIdType.GENERAL);
//
//    assertThat(user)
//        .isEqualToComparingOnlyGivenFields(dataUser, "username", "email");
//    assertThat(super.idMapper.mapUserIdToRawId(user.userId()))
//        .isEqualTo(dataUser.id());
//    assertThat(user.password())
//        .isEqualTo(dataUser.enc_password());
//  }
}
