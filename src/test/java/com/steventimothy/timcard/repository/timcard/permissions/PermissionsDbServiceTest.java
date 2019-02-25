package com.steventimothy.timcard.repository.timcard.permissions;

import com.steventimothy.timcard.repository.schemas.DataPermission;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class PermissionsDbServiceTest extends PermissionsBaseComponent {

  /**
   * Tests that a data permission can be inserted into the database.
   */
  @Test
  public void testInsert_Valid() {
    DataPermission dataPermission = createLocalDataPermission();

    Long id = insert(dataPermission);

    assertThat(id)
        .isNotNull();
    assertThat(dataPermission.id())
        .isNotNull()
        .isEqualTo(id);
  }

  /**
   * Tests that a data permission with invalid data throws a DatabaseDataException.
   */
  @Test(expected = DatabaseDataException.class)
  public void testInsert_Invalid() {
    insert(new DataPermission());
  }

  /**
   * Tests that a data permission can be retrieved by id.
   */
  @Test
  public void testGet_Id_Valid() {
    DataPermission dataPermission = createLocalDataPermission();
    Long id = insert(dataPermission);

    DataPermission dataPermission2 = get(id);

    assertThat(dataPermission2)
        .isNotNull()
        .isEqualToIgnoringNullFields(dataPermission);
  }

  /**
   * Tests that null is returned when trying to get a data permission with an id
   * that is not found.
   */
  @Test
  public void testGet_Id_NotFound() {
    DataPermission dataPermission = get(0L);

    assertThat(dataPermission)
        .isNull();
  }

  /**
   * Tests that a data permission can be retrieved from the database given a name.
   */
  @Test
  public void testGet_Name_Valid() {
    DataPermission dataPermission = createLocalDataPermission();
    insert(dataPermission);

    DataPermission dataPermission2 = get(dataPermission.name());

    assertThat(dataPermission2)
        .isNotNull()
        .isEqualToIgnoringNullFields(dataPermission);
  }

  /**
   * Tests that get returns null if given an empty name that is not found.
   */
  @Test
  public void testGet_Name_NotFound() {
    DataPermission dataPermission = get("");

    assertThat(dataPermission)
        .isNull();
  }

  /**
   * Tests that a data permission can be updated.
   */
  @Test
  public void testUpdate_Valid() {
    DataPermission dataPermission = createLocalDataPermission();
    DataPermission dataPermission2 = createAltLocalDataPermission();
    Long id = insert(dataPermission);
    dataPermission2.id(id);
    dataPermission2.last_modified(Timestamp.from(Instant.now()));

    assertThat(update(dataPermission2))
        .isTrue();

    DataPermission dataPermission3 = get(id);
    assertThat(dataPermission3)
        .isNotNull()
        .isEqualToIgnoringGivenFields(dataPermission2, "date_created", "last_modified");
  }

  /**
   * Tests that updating a data permission with bad data throws a DatabaseDataException.
   */
  @Test(expected = DatabaseDataException.class)
  public void testUpdate_Invalid() {
    DataPermission dataPermission = createLocalDataPermission();
    DataPermission dataPermission2 = createAltLocalDataPermission();
    insert(dataPermission);
    insert(dataPermission2);
    dataPermission2.name(dataPermission.name());

    update(dataPermission2);
  }

  /**
   * Tests that a data permission can be deleted by id.
   */
  @Test
  public void testDelete_Id_Valid() {
    DataPermission dataPermission = createLocalDataPermission();
    Long id = insert(dataPermission);

    assertThat(delete(id))
        .isTrue();

    DataPermission dataPermission2 = get(id);
    assertThat(dataPermission2)
        .isNull();
  }

  /**
   * Tests that delete returns false if deleting a data permission that doesn't exist by id.
   */
  @Test
  public void testDelete_Id_NotFound() {
    assertThat(delete(0L))
        .isFalse();
  }

  /**
   * Tests that a data permission can be deleted by name.
   */
  @Test
  public void testDelete_Name_Valid() {
    DataPermission dataPermission = createLocalDataPermission();
    Long id = insert(dataPermission);

    assertThat(delete(dataPermission.name()))
        .isTrue();

    DataPermission dataPermission2 = get(id);
    assertThat(dataPermission2)
        .isNull();
  }

  /**
   * Tests that delete returns false if the data permission doesn't exist by name.
   */
  @Test
  public void testDelete_Name_NotFound() {
    assertThat(delete(""))
        .isFalse();
  }
}
