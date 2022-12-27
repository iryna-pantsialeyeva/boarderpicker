CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS roles (
  id INT NOT NULL AUTO_INCREMENT,
  role VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS producers (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS games (
    id INT NOT NULL AUTO_INCREMENT,
    gamename VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    producers_id INT NOT NULL,
    picpath VARCHAR(255) NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (producers_id)
    REFERENCES producers (id)
);

CREATE TABLE IF NOT EXISTS users_roles (
    users_id INT NOT NULL,
    roles_id INT NOT NULL,
    PRIMARY KEY (users_id, roles_id),
    FOREIGN KEY (users_id)
    REFERENCES users (id),
    FOREIGN KEY (roles_id)
    REFERENCES roles (id)
    );

CREATE TABLE IF NOT EXISTS sales (
    id INT NOT NULL AUTO_INCREMENT,
    price DOUBLE NOT NULL,
    users_id INT NOT NULL,
    games_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (users_id)
    REFERENCES users (id),
    FOREIGN KEY (games_id)
    REFERENCES games (id)
    );

CREATE TABLE IF NOT EXISTS games_categories (
    games_id INT NOT NULL,
    categories_id INT NOT NULL,
    PRIMARY KEY (games_id, categories_id),
    FOREIGN KEY (games_id)
    REFERENCES games (id),
    FOREIGN KEY (categories_id)
    REFERENCES categories (id)
    );









