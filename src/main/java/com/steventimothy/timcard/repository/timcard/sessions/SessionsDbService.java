package com.steventimothy.timcard.repository.timcard.sessions;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.schemas.DataSession;
import com.steventimothy.timcard.repository.timcard.TimcardDbService;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import com.steventimothy.timcard.repository.timcard.sessions.config.SessionsDbConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h1>The RolePermissionsDbService Class</h1>
 * <p>This class is responsible for talking with the role_permissions table in the database.</p>
 */
@Slf4j
@Component
public class SessionsDbService extends TimcardDbService {

  /**
   * The configurations for the role_permissions table.
   */
  private SessionsDbConfig dbConfig;

  DataSession get(String session_id) {
    DataSession dataSession = null;

    Connection connection = openConnection();

    deleteExpiredSessions(connection);

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + dbConfig.getTableName() + " WHERE session_id = ?");
      preparedStatement.setString(1, session_id);

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      //Get the dataUser.
      if (resultSet.next()) {
        dataSession = new DataSession()
            .id(resultSet.getLong("id"))
            .session_id(resultSet.getString("session_id"))
            .user_id(resultSet.getString("user_id"))
            .expiration(resultSet.getTimestamp("expiration").toInstant())
            .date_created(resultSet.getTimestamp("date_created").toInstant())
            .last_modified(resultSet.getTimestamp("last_modified").toInstant());
      }
    }
    catch (SQLException ex) {
      throw new UnsupportedOperationException("get Exception handling not implemented yet.");
    }

    //Close the connection.
    closeConnection(connection);

    return dataSession;
  }

  private boolean deleteExpiredSessions(Connection connection) {
    int affectedRows = 0;

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + dbConfig.getTableName() + " WHERE expiration < NOW()");

      affectedRows = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      throw new UnsupportedOperationException("Sql exception not implemented yet.");
    }

    return (affectedRows > 0);
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
  public SessionsDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, SessionsDbConfig sessionsDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, timcardDbConfig, mysqlDataSource);
    this.dbConfig = sessionsDbConfig;
  }
}
