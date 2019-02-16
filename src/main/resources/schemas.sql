-- Database Timcard
CREATE DATABASE timcard;

-- User Ids table
CREATE TABLE IF NOT EXISTS user_ids
(
  id      BIGINT  NOT NULL AUTO_INCREMENT,
  user_id CHAR(5) NOT NULL UNIQUE,
  used    BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (id)
);

-- Users Table
CREATE TABLE IF NOT EXISTS users
(
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  user_id       CHAR(5)      NOT NULL UNIQUE,
  username      VARCHAR(50)  NOT NULL UNIQUE,
  email         VARCHAR(100) NOT NULL UNIQUE,
  password      CHAR(88)     NOT NULL,
  salt          CHAR(88)     NOT NULL,
  date_created  DATETIME     NOT NULL DEFAULT NOW(),
  last_modified DATETIME     NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user_ids (user_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  UNIQUE INDEX usr_pass_ind (username, password),
  UNIQUE INDEX eml_pass_ind (email, password)
);

-- users to user_id used trigger
DELIMITER $$
CREATE TRIGGER user_id_trig
  AFTER DELETE ON users
  FOR EACH ROW
BEGIN
  UPDATE user_ids SET used = 0
  WHERE user_id = old.user_id;
END$$
DELIMITER ;

-- Sessions Table
CREATE TABLE IF NOT EXISTS sessions
(
  id            BIGINT   NOT NULL AUTO_INCREMENT,
  session_id    CHAR(36) NOT NULL UNIQUE,
  user_id       CHAR(5)  NOT NULL,
  expiration    DATETIME NOT NULL,
  date_created  DATETIME NOT NULL DEFAULT NOW(),
  last_modified DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  INDEX usr_ind (user_id)
);

-- Roles Table
CREATE TABLE IF NOT EXISTS roles
(
  id            BIGINT      NOT NULL AUTO_INCREMENT,
  name          VARCHAR(50) NOT NULL UNIQUE,
  date_created  DATETIME    NOT NULL DEFAULT NOW(),
  last_modified DATETIME    NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id)
);

-- Permissions Table
CREATE TABLE permissions
(
  id            BIGINT      NOT NULL AUTO_INCREMENT,
  name          VARCHAR(50) NOT NULL UNIQUE,
  date_created  DATETIME    NOT NULL DEFAULT NOW(),
  last_modified DATETIME    NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id)
);

-- Role Permissions Table
CREATE TABLE IF NOT EXISTS role_permissions
(
  id            BIGINT   NOT NULL AUTO_INCREMENT,
  role_id       BIGINT   NOT NULL,
  permission_id BIGINT   NOT NULL,
  date_created  DATETIME NOT NULL DEFAULT NOW(),
  last_modified DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  UNIQUE (role_id, permission_id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (permission_id) REFERENCES permissions (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  INDEX role_ind (role_id),
  INDEX permission_ind (permission_id)
);

-- User Roles Table
CREATE TABLE IF NOT EXISTS user_roles
(
  id            BIGINT   NOT NULL AUTO_INCREMENT,
  user_id       CHAR(5)  NOT NULL,
  role_id       BIGINT   NOT NULL,
  date_created  DATETIME NOT NULL DEFAULT NOW(),
  last_modified DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  UNIQUE (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  INDEX usr_ind (user_id),
  INDEX role_ind (role_id)
);
