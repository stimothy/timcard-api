package com.steventimothy.timcard.repository.timcard.roles;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataRole;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.roles.config.RolesDbConfig;
import com.steventimothy.timcard.schemas.exceptions.DatabaseDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h1>The RolesDbService Class</h1>
 * <p>This class is responsible for talking with the roles table in the database.</p>
 */
@Slf4j
@Component
class RolesDbService extends TimcardDbService {

  /**
   * The config for the roles table.
   */
  private RolesDbConfig dbConfig;

  DataRole insert(String name) {

  }

  DataRole get(Long id) {

  }

  /**
   * Gets the data role by name.
   *
   * @param name The name of the role.
   * @return The data role that matches the name.
   * @throws DatabaseDataException throws if the data used in the query was bad.
   */
  DataRole get(String name)
      throws DatabaseDataException {

    DataRole dataRole = null;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE name = ?");
      preparedStatement.setString(1, name);

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      //Get the dataUser.
      if (resultSet.next()) {
        dataRole = new DataRole()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .date_created(resultSet.getTimestamp("date_created").toInstant())
            .last_modified(resultSet.getTimestamp("last_modified").toInstant());
      }
    }
    catch (SQLException ex) {
      throw new DatabaseDataException("The data used in the query was bad.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataRole;
  }

  Boolean update(DataRole dataRole) {

  }

  Boolean delete(Long id) {

  }

  Boolean delete(String name) {

  }


  /**
   * Constructor.
   *
   * @param dbConfig        The configurations for the database system.
   * @param timcardDbConfig The configurations for the timcard database.
   * @param rolesDbConfig   The configurations for the roles table.
   * @param mysqlDataSource The mysql datasource used to connect to the database.
   */
  @Autowired
  RolesDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, RolesDbConfig rolesDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = rolesDbConfig;
  }
}
