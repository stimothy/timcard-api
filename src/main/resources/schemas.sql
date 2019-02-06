-- Database Timcard
CREATE DATABASE timcard;

-- Users Table
CREATE TABLE users
(
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  username      VARCHAR(50)  NOT NULL UNIQUE,
  email         VARCHAR(100) NOT NULL UNIQUE,
  enc_password  CHAR(64)     NOT NULL,
  date_created  DATETIME     NOT NULL,
  last_modified DATETIME     NOT NULL,
  PRIMARY KEY (id),
  INDEX usr_pass_ind (username, enc_password),
  INDEX eml_pass_ind (email, enc_password)
) AUTO_INCREMENT = 101;

-- Sessions Table
CREATE TABLE sessions
(
  id            CHAR(36) NOT NULL,
  user_id       BIGINT   NOT NULL,
  expiration    DATETIME NOT NULL,
  date_created  DATETIME NOT NULL,
  last_modified DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  INDEX usr_ind (user_id)
);

-- Roles Table
CREATE TABLE roles
(
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (id)
) AUTO_INCREMENT = 101;

-- Permissions Table
CREATE TABLE permissions
(
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (id)
) AUTO_INCREMENT = 101;

-- Role Permissions Table
CREATE TABLE role_permissions
(
  role_id       BIGINT NOT NULL,
  permission_id BIGINT NOT NULL,
  PRIMARY KEY (role_id, permission_id),
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
CREATE TABLE user_roles
(
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles (id)
    ON UPDATE CASCADE
    ON DELETe CASCADE,
  INDEX usr_ind (user_id),
  INDEX role_ind (role_id)
);
