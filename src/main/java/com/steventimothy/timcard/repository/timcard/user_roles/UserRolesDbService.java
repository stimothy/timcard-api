package com.steventimothy.timcard.repository.timcard.user_roles;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataUserRole;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.user_roles.config.UserRolesDbConfig;
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
public class UserRolesDbService extends TimcardDbService {

  /**
   * The configurations for the role_permissions table.
   */
  private UserRolesDbConfig dbConfig;

  void insert(String user_id, Long role_id) {
    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dbConfig.getTableName() + " (user_id, role_id) VALUES(?, ?)");
      preparedStatement.setString(1, user_id);
      preparedStatement.setLong(2, role_id);

      preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new UnsupportedOperationException("Not supported.");
    }

    closeConnection(connection);
  }

  List<DataUserRole> getAll(String user_id) {
    List<DataUserRole> dataUserRoles = new ArrayList<>();

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE user_id = ?");
      preparedStatement.setString(1, user_id);

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      //Get the dataUser.
      while (resultSet.next()) {
        dataUserRoles.add(new DataUserRole()
            .id(resultSet.getLong("id"))
            .user_id(resultSet.getString("user_id"))
            .role_id(resultSet.getLong("role_id"))
            .date_created(resultSet.getTimestamp("date_created").toInstant())
            .last_modified(resultSet.getTimestamp("last_modified").toInstant())
        );
      }
    }
    catch (SQLException ex) {
      throw new UnsupportedOperationException("Exceptions not implemented yet.");
    }

    //Close the connection.
    closeConnection(connection);

    return dataUserRoles;
  }

  /**
   * The constructor that receives the autowired components.
   *
   * @param dbConfig                The dbms config.
   * @param mysqlDataSource         The datasource object for dbms.
   * @param timcardDbConfig         The timcard db config.
   * @param rolePermissionsDbConfig The users table config.
   */
  @Autowired
  public UserRolesDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, UserRolesDbConfig userRolesDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = userRolesDbConfig;
  }
}