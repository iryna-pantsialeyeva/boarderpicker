CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  userName VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS roles (
  id INT NOT NULL AUTO_INCREMENT,
  role VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS producer (
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
    gameName VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) NULL,
    producer_id INT NOT NULL,
    categories_id INT NOT NULL,
    picpath VARCHAR(255) NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (producer_id)
    REFERENCES producer (id),
    FOREIGN KEY (categories_id)
    REFERENCES categories (id)
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
    users_id INT NOT NULL,
    games_id INT NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (users_id, games_id),
    FOREIGN KEY (users_id)
    REFERENCES users (id),
    FOREIGN KEY (games_id)
    REFERENCES games (id)
    );

CREATE TABLE IF NOT EXISTS wishlists (
    users_id INT NOT NULL,
    games_id INT NOT NULL,
    PRIMARY KEY (users_id, games_id),
    FOREIGN KEY (users_id)
    REFERENCES users (id),
    FOREIGN KEY (games_id)
    REFERENCES games (id)
    );