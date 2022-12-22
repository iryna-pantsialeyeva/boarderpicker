-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema boarderpicker
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema boarderpicker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boarderpicker` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `boarderpicker` ;

-- -----------------------------------------------------
-- Table `boarderpicker`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`users` (
  `id` INT NOT NULL,
  `userName` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boarderpicker`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`roles` (
  `id` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boarderpicker`.`producer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`producer` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boarderpicker`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`categories` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boarderpicker`.`games`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`games` (
  `id` INT NOT NULL,
  `gameName` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `producer_id` INT NOT NULL,
  `categories_id` INT NOT NULL,
  `picpath` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `gameName_UNIQUE` (`gameName` ASC) VISIBLE,
  INDEX `fk_games_producer1_idx` (`producer_id` ASC) VISIBLE,
  INDEX `fk_games_categories1_idx` (`categories_id` ASC) VISIBLE,
  CONSTRAINT `fk_games_producer1`
    FOREIGN KEY (`producer_id`)
    REFERENCES `boarderpicker`.`producer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_games_categories1`
    FOREIGN KEY (`categories_id`)
    REFERENCES `boarderpicker`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boarderpicker`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`users_roles` (
  `users_id` INT NOT NULL,
  `roles_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `roles_id`),
  INDEX `fk_users_has_roles_roles1_idx` (`roles_id` ASC) VISIBLE,
  INDEX `fk_users_has_roles_users_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_roles_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `boarderpicker`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_roles_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `boarderpicker`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boarderpicker`.`sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`sales` (
  `users_id` INT NOT NULL,
  `games_id` INT NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`users_id`, `games_id`),
  INDEX `fk_users_has_games_games1_idx` (`games_id` ASC) VISIBLE,
  INDEX `fk_users_has_games_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_games_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `boarderpicker`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_games_games1`
    FOREIGN KEY (`games_id`)
    REFERENCES `boarderpicker`.`games` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boarderpicker`.`wishlists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`wishlists` (
  `users_id` INT NOT NULL,
  `games_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `games_id`),
  INDEX `fk_users_has_games_games2_idx` (`games_id` ASC) VISIBLE,
  INDEX `fk_users_has_games_users2_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_games_users2`
    FOREIGN KEY (`users_id`)
    REFERENCES `boarderpicker`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_games_games2`
    FOREIGN KEY (`games_id`)
    REFERENCES `boarderpicker`.`games` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `boarderpicker`.`communication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boarderpicker`.`communication` (
)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
