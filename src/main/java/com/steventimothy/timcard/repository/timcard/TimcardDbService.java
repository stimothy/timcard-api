package com.steventimothy.timcard.repository.timcard;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timcard.repository.DbService;
import com.steventimothy.timcard.repository.config.DbConfig;
import com.steventimothy.timcard.repository.timcard.config.TimcardDbConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * <h1>The TimcardDbService Class</h1>
 * <p>This class is responsible for talking with the timcard database and establishing connections.</p>
 */
@Slf4j
@Component
public abstract class TimcardDbService extends DbService {

  /**
   * The timcard database config.
   */
  private TimcardDbConfig dbConfig;

  /**
   * Open a connection with the database.
   *
   * @return The connection to the database.
   */
  protected Connection openConnection() {
    return super.openConnection(dbConfig.getDatabaseName());
  }

  /**
   * Close the connection with the database.
   *
   * @param connection The connection to close.
   */
  @Override
  protected void closeConnection(Connection connection) {
    super.closeConnection(connection);
  }

  /**
   * The constructor the set up the injections.
   *
   * @param dbConfig        The dbms config class.
   * @param timcardDbConfig The timcard db config class.
   * @param mysqlDataSource The datasource for connecting with a database.
   */
  public TimcardDbService(DbConfig dbConfig, TimcardDbConfig timcardDbConfig, MysqlDataSource mysqlDataSource) {
    super(dbConfig, mysqlDataSource);
    this.dbConfig = timcardDbConfig;
  }
}
