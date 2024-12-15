-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BeatTheHouseDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BeatTheHouseDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BeatTheHouseDB` DEFAULT CHARACTER SET utf8 ;
USE `BeatTheHouseDB` ;

-- -----------------------------------------------------
-- Table `BeatTheHouseDB`.`user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BeatTheHouseDB`.`user_profile` (
  `user_profile_id` INT NOT NULL,
  `profile_name` VARCHAR(20) NULL,
  `current_card_apearance` INT NULL,
  `current_table_apearance` INT NULL,
  `current_profile_icon` INT NULL,
  PRIMARY KEY (`user_profile_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BeatTheHouseDB`.`stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BeatTheHouseDB`.`stats` (
  `idstats` INT NOT NULL,
  `chip_count` INT NULL,
  `wins` INT NULL,
  `losses` INT NULL,
  `chips_won` INT NULL,
  `chips_lost` INT NULL,
  `total_winnings` INT NULL,
  `times_bankrupt` INT NULL,
  `highest_chip_count` INT NULL,
  `user_profile_id` INT NOT NULL,
  PRIMARY KEY (`idstats`, `user_profile_id`),
  INDEX `fk_stats_user_profile_idx` (`user_profile_id` ASC) VISIBLE,
  CONSTRAINT `fk_stats_user_profile`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `BeatTheHouseDB`.`user_profile` (`user_profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BeatTheHouseDB`.`achievements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BeatTheHouseDB`.`achievements` (
  `achievement_id` INT NOT NULL,
  `achievement1` TINYINT NULL,
  `achievement2` TINYINT NULL,
  `achievement3` TINYINT NULL,
  `achievement4` TINYINT NULL,
  `achievement5` TINYINT NULL,
  `achievement6` TINYINT NULL,
  `achievement7` TINYINT NULL,
  `achievement8` TINYINT NULL,
  `achievement9` TINYINT NULL,
  `achievement10` TINYINT NULL,
  `user_profile_id` INT NOT NULL,
  PRIMARY KEY (`achievement_id`, `user_profile_id`),
  INDEX `fk_achievements_user_profile1_idx` (`user_profile_id` ASC) VISIBLE,
  CONSTRAINT `fk_achievements_user_profile1`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `BeatTheHouseDB`.`user_profile` (`user_profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
