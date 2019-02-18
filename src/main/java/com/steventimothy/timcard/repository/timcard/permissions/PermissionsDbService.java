package com.steventimothy.timcard.repository.timcard.permissions;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataPermission;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.permissions.config.PermissionsDbConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h1>The PermissionsDbService Class</h1>
 * <p>This class is responsible for talking with the permissions table in the database.</p>
 */
@Slf4j
@Component
class PermissionsDbService extends TimcardDbService {

  /**
   * The config for the permissions table.
   */
  private PermissionsDbConfig dbConfig;

  /**
   * Gets a DataPermission from the database by id.
   *
   * @param id The id of the permission
   * @return The data permission matching the id.
   */
  DataPermission get(Long id) {
    DataPermission dataPermission = null;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE id = ?");
      preparedStatement.setLong(1, id);

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      //Get the dataUser.
      if (resultSet.next()) {
        dataPermission = new DataPermission()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .date_created(resultSet.getTimestamp("date_created").toInstant())
            .last_modified(resultSet.getTimestamp("last_modified").toInstant());
      }
    }
    catch (SQLException ex) {
      throw new UnsupportedOperationException("get Exception handling not implemented yet.");
    }

    //Close the connection.
    closeConnection(connection);

    return dataPermission;
  }

  /**
   * The constructor.
   *
   * @param dbConfig            The configurations for the entire database system.
   * @param timcardDbConfig     The configurations for the timcard database.
   * @param permissionsDbConfig The configurations for the permissions table.
   * @param mysqlDataSource     The mysql data source used to communicate with the database.
   */
  @Autowired
  PermissionsDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, PermissionsDbConfig permissionsDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = permissionsDbConfig;
  }
}
