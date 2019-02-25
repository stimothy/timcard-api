package com.steventimothy.timcard.repository.timcard.roles;

import com.steventimothy.timcard.repository.schemas.DataRole;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class RolesDbServiceTest extends RolesBaseComponent {

  /**
   * Tests that a data role can be inserted into the database.
   */
  @Test
  public void testInsert_Valid() {
    DataRole dataRole = createLocalDataRole();

    Long id = insert(dataRole);

    assertThat(id)
        .isNotNull();
    assertThat(dataRole.id())
        .isNotNull()
        .isEqualTo(id);
  }

  /**
   * Tests that a data role with invalid data throws a DatabaseDataException.
   */
  @Test(expected = DatabaseDataException.class)
  public void testInsert_Invalid() {
    insert(new DataRole());
  }

  /**
   * Tests that a data role can be retrieved by id.
   */
  @Test
  public void testGet_Id_Valid() {
    DataRole dataRole = createLocalDataRole();
    Long id = insert(dataRole);

    DataRole dataRole2 = get(id);

    assertThat(dataRole2)
        .isNotNull()
        .isEqualToIgnoringNullFields(dataRole);
  }

  /**
   * Tests that null is returned when trying to get a data role with an id
   * that is not found.
   */
  @Test
  public void testGet_Id_NotFound() {
    DataRole dataRole = get(0L);

    assertThat(dataRole)
        .isNull();
  }

  /**
   * Tests that a data role can be retrieved from the database given a name.
   */
  @Test
  public void testGet_Name_Valid() {
    DataRole dataRole = createLocalDataRole();
    insert(dataRole);

    DataRole dataRole2 = get(dataRole.name());

    assertThat(dataRole2)
        .isNotNull()
        .isEqualToIgnoringNullFields(dataRole);
  }

  /**
   * Tests that get returns null if given an empty name that is not found.
   */
  @Test
  public void testGet_Name_NotFound() {
    DataRole dataRole = get("");

    assertThat(dataRole)
        .isNull();
  }

  /**
   * Tests that a data role can be updated.
   */
  @Test
  public void testUpdate_Valid() {
    DataRole dataRole = createLocalDataRole();
    DataRole dataRole2 = createAltLocalDataRole();
    Long id = insert(dataRole);
    dataRole2.id(id);
    dataRole2.last_modified(Timestamp.from(Instant.now()));

    assertThat(update(dataRole2))
        .isTrue();

    DataRole dataRole3 = get(id);
    assertThat(dataRole3)
        .isNotNull()
        .isEqualToIgnoringGivenFields(dataRole2, "date_created", "last_modified");
  }

  /**
   * Tests that updating a data role with bad data throws a DatabaseDataException.
   */
  @Test(expected = DatabaseDataException.class)
  public void testUpdate_Invalid() {
    DataRole dataRole = createLocalDataRole();
    DataRole dataRole2 = createAltLocalDataRole();
    insert(dataRole);
    insert(dataRole2);
    dataRole2.name(dataRole.name());

    update(dataRole2);
  }

  /**
   * Tests that a data role can be deleted by id.
   */
  @Test
  public void testDelete_Id_Valid() {
    DataRole dataRole = createLocalDataRole();
    Long id = insert(dataRole);

    assertThat(delete(id))
        .isTrue();

    DataRole dataRole2 = get(id);
    assertThat(dataRole2)
        .isNull();
  }

  /**
   * Tests that delete returns false if deleting a data role that doesn't exist by id.
   */
  @Test
  public void testDelete_Id_NotFound() {
    assertThat(delete(0L))
        .isFalse();
  }

  /**
   * Tests that a data role can be deleted by name.
   */
  @Test
  public void testDelete_Name_Valid() {
    DataRole dataRole = createLocalDataRole();
    Long id = insert(dataRole);

    assertThat(delete(dataRole.name()))
        .isTrue();

    DataRole dataRole2 = get(id);
    assertThat(dataRole2)
        .isNull();
  }

  /**
   * Tests that delete returns false if the data role doesn't exist by name.
   */
  @Test
  public void testDelete_Name_NotFound() {
    assertThat(delete(""))
        .isFalse();
  }
}
