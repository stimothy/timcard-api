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
   * Gets all the data permissions that are linked to the role id.
   *
   * @param role_Id The id of the role.
   * @return The data permissions that are linked to that role id.
   * @throws DatabaseDataException throws if the data passed to the database was bad.
   */
  List<DataRolePermission> getAllByRoleId(Long role_Id) throws DatabaseDataException {
    List<DataRolePermission> dataRolePermissions = new ArrayList<>();

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE role_id = ?");
      preparedStatement.setLong(1, role_Id);

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      //Get the dataUser.
      while (resultSet.next()) {
        dataRolePermissions.add(new DataRolePermission()
            .id(resultSet.getLong("id"))
            .role_id(resultSet.getLong("role_id"))
            .permission_id(resultSet.getLong("permission_id"))
            .date_created(resultSet.getTimestamp("date_created").toInstant())
            .last_modified(resultSet.getTimestamp("last_modified").toInstant()));
      }
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used to query the database was bad.", ex);
    }

    //Close the connection.
    closeConnection(connection);

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
