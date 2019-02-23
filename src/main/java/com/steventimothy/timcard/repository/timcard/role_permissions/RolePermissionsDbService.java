package com.steventimothy.timcard.repository.timcard.role_permissions;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataRolePermission;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.role_permissions.config.RolePermissionsDbConfig;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>The RolePermissionsDbService Class</h1>
 * <p>This class is responsible for talking with the role_permissions table in the database.</p>
 */
@Slf4j
@Component
class RolePermissionsDbService extends TimcardDbService {

  /**
   * The configurations for the role_permissions table.
   */
  private RolePermissionsDbConfig dbConfig;

  /**
   * Inserts a data role permission into the database.
   * @param dataRolePermission The data role permission to insert.
   * @return The newly created data role permission.
   * @throws DatabaseDataException throws if there was a conflict with the data
   * in the query.
   */
  DataRolePermission insert(DataRolePermission dataRolePermission)
      throws DatabaseDataException {

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dbConfig.getTableName() + " (role_id, permission_id) VALUES(?, ?)");
      preparedStatement.setLong(1, dataRolePermission.role_id());
      preparedStatement.setLong(2, dataRolePermission.permission_id());

      preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query data.", ex);
    }

    closeConnection(connection);

    return get(dataRolePermission);
  }

  /**
   * Gets a data role permission with the id.
   * @param dataRolePermission The data role permission to retrieve.
   * @return The data role permission containing the id.
   * @throws DatabaseDataException throws if there was a conflict with the data
   * in the query.
   */
  DataRolePermission get(DataRolePermission dataRolePermission)
      throws DatabaseDataException {

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE role_id = ? AND permission_id = ?");
      preparedStatement.setLong(1, dataRolePermission.role_id());
      preparedStatement.setLong(2, dataRolePermission.permission_id());

      dataRolePermission = getDataRolePermission(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query data.", ex);
    }

    closeConnection(connection);

    return dataRolePermission;
  }

  /**
   * Gets a data role permission.
   * @param id The id of the data role permission to retrieve.
   * @return The data role permission matching the id.
   * @throws DatabaseDataException throws if there was a conflict with the data
   * in the query.
   */
  DataRolePermission get(Long id)
      throws DatabaseDataException {

    DataRolePermission dataRolePermission;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE id = ?");
      preparedStatement.setLong(1, id);

      dataRolePermission = getDataRolePermission(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("There was a problem with the query data.", ex);
    }

    closeConnection(connection);

    return dataRolePermission;
  }

  /**
   * Gets all the data permissions that are linked to the role id.
   *
   * @param role_Id The id of the role.
   * @return The data permissions that are linked to that role id.
   * @throws DatabaseDataException throws if the data passed to the database was bad.
   */
  List<DataRolePermission> getAllByRoleId(Long role_Id) throws DatabaseDataException {
    List<DataRolePermission> dataRolePermissions;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE role_id = ?");
      preparedStatement.setLong(1, role_Id);

      dataRolePermissions = getAllDataRolePermission(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used to query the database was bad.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataRolePermissions;
  }

  /**
   * Gets all the data permissions that are linked to the permission id.
   *
   * @param permission_id The id of the permission.
   * @return The data permissions that are linked to that permission id.
   * @throws DatabaseDataException throws if the data passed to the database was bad.
   */
  List<DataRolePermission> getAllByPermissionId(Long permission_id) throws DatabaseDataException {
    List<DataRolePermission> dataRolePermissions;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE permission_id = ?");
      preparedStatement.setLong(1, permission_id);

      dataRolePermissions = getAllDataRolePermission(preparedStatement);
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used to query the database was bad.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataRolePermissions;
  }

  Boolean update(DataRolePermission dataRolePermission) {

  }

  Boolean delete(Long id) {

  }

  Boolean deleteAllByRoleId(Long role_id) {

  }

  Boolean deleteAllByPermissionId(Long permission_id) {

  }

  /**
   * Get a data role permission from a prepared statement.
   * @param preparedStatement The prepared statement to query the database.
   * @return The DataRolePermission in the database.
   * @throws SQLException Throws if something went wrong with the data query.
   */
  private DataRolePermission getDataRolePermission(PreparedStatement preparedStatement)
      throws SQLException {

    //Execute the statement
    ResultSet resultSet = preparedStatement.executeQuery();

    //Get the dataPermission.
    if (resultSet.next()) {
      return new DataRolePermission()
          .id(resultSet.getLong("id"))
          .role_id(resultSet.getLong("role_id"))
          .permission_id(resultSet.getLong("permission_id"))
          .date_created(resultSet.getTimestamp("date_created").toInstant())
          .last_modified(resultSet.getTimestamp("last_modified").toInstant());
    }
    else {
      return null;
    }
  }

  /**
   * Get all data role permission from a prepared statement.
   * @param preparedStatement The prepared statement to query the database.
   * @return A list of DataRolePermission in the database.
   * @throws SQLException Throws if something went wrong with the data query.
   */
  private List<DataRolePermission> getAllDataRolePermission(PreparedStatement preparedStatement)
      throws SQLException {

    List<DataRolePermission> dataRolePermissions = new ArrayList<>();

    //Execute the statement
    ResultSet resultSet = preparedStatement.executeQuery();

    //Get the dataPermission.
    while (resultSet.next()) {
      dataRolePermissions.add(new DataRolePermission()
          .id(resultSet.getLong("id"))
          .role_id(resultSet.getLong("role_id"))
          .permission_id(resultSet.getLong("permission_id"))
          .date_created(resultSet.getTimestamp("date_created").toInstant())
          .last_modified(resultSet.getTimestamp("last_modified").toInstant())
      );
    }

    return dataRolePermissions;
  }


  /**
   * The constructor that receives the autowired components.
   *
   * @param dbConfig                The dbms config.
   * @param mysqlDataSource         The datasource object for dbms.
   * @param timcardDbConfig         The timcard db config.
   * @param rolePermissionsDbConfig The role_permissions table config.
   */
  @Autowired
  RolePermissionsDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, RolePermissionsDbConfig rolePermissionsDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = rolePermissionsDbConfig;
  }
}
