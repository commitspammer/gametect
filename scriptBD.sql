-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gametect
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gametect
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gametect` DEFAULT CHARACTER SET utf8 ;
USE `gametect` ;

-- -----------------------------------------------------
-- Table `gametect`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(15) NOT NULL,
  `senha` VARCHAR(30) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `foto` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`framework`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`framework` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(15) NOT NULL,
  `tutorial` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`projeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`projeto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(30) NOT NULL,
  `banner` VARCHAR(45) NOT NULL,
  `descricao` MEDIUMTEXT NOT NULL,
  `framework_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_projeto_framework1_idx` (`framework_id` ASC) VISIBLE,
  CONSTRAINT `fk_projeto_framework1`
    FOREIGN KEY (`framework_id`)
    REFERENCES `gametect`.`framework` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`projeto_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`projeto_usuario` (
  `projeto_id` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`projeto_id`, `usuario_id`),
  INDEX `fk_projeto_has_usuario_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_projeto_has_usuario_projeto_idx` (`projeto_id` ASC) VISIBLE,
  CONSTRAINT `fk_projeto_has_usuario_projeto`
    FOREIGN KEY (`projeto_id`)
    REFERENCES `gametect`.`projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_projeto_has_usuario_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `gametect`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`papel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`papel` (
  `projeto_usuario_projeto_id` INT NOT NULL,
  `projeto_usuario_usuario_id` INT NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`projeto_usuario_projeto_id`, `projeto_usuario_usuario_id`, `descricao`),
  CONSTRAINT `fk_papel_projeto_usuario1`
    FOREIGN KEY (`projeto_usuario_projeto_id` , `projeto_usuario_usuario_id`)
    REFERENCES `gametect`.`projeto_usuario` (`projeto_id` , `usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`secao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`secao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(30) NOT NULL,
  `descricao` MEDIUMTEXT NULL,
  `framework_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_secao_framework1_idx` (`framework_id` ASC) VISIBLE,
  CONSTRAINT `fk_secao_framework1`
    FOREIGN KEY (`framework_id`)
    REFERENCES `gametect`.`framework` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`subsecao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`subsecao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(30) NOT NULL,
  `decricao` MEDIUMTEXT NULL,
  `secao_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_subsecao_secao1_idx` (`secao_id` ASC) VISIBLE,
  CONSTRAINT `fk_subsecao_secao1`
    FOREIGN KEY (`secao_id`)
    REFERENCES `gametect`.`secao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`caixa_texto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`caixa_texto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `texto` LONGTEXT NOT NULL,
  `subsecao_id` INT NOT NULL,
  `projeto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_caixa_texto_subsecao1_idx` (`subsecao_id` ASC) VISIBLE,
  INDEX `fk_caixa_texto_projeto1_idx` (`projeto_id` ASC) VISIBLE,
  CONSTRAINT `fk_caixa_texto_subsecao1`
    FOREIGN KEY (`subsecao_id`)
    REFERENCES `gametect`.`subsecao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_caixa_texto_projeto1`
    FOREIGN KEY (`projeto_id`)
    REFERENCES `gametect`.`projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`ficha_personagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`ficha_personagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `folha_referencia` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(25) NOT NULL,
  `altura` FLOAT NULL,
  `peso` FLOAT NULL,
  `passado` MEDIUMTEXT NULL,
  `motivacao` MEDIUMTEXT NULL,
  `subsecao_id` INT NOT NULL,
  `projeto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ficha_personagem_subsecao1_idx` (`subsecao_id` ASC) VISIBLE,
  INDEX `fk_ficha_personagem_projeto1_idx` (`projeto_id` ASC) VISIBLE,
  CONSTRAINT `fk_ficha_personagem_subsecao1`
    FOREIGN KEY (`subsecao_id`)
    REFERENCES `gametect`.`subsecao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ficha_personagem_projeto1`
    FOREIGN KEY (`projeto_id`)
    REFERENCES `gametect`.`projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`imagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `largura` FLOAT NOT NULL,
  `altura` FLOAT NOT NULL,
  `url` VARCHAR(45) NOT NULL,
  `subsecao_id` INT NOT NULL,
  `projeto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_imagem_subsecao1_idx` (`subsecao_id` ASC) VISIBLE,
  INDEX `fk_imagem_projeto1_idx` (`projeto_id` ASC) VISIBLE,
  CONSTRAINT `fk_imagem_subsecao1`
    FOREIGN KEY (`subsecao_id`)
    REFERENCES `gametect`.`subsecao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_imagem_projeto1`
    FOREIGN KEY (`projeto_id`)
    REFERENCES `gametect`.`projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`caixa_selecao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`caixa_selecao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `questao` VARCHAR(30) NOT NULL,
  `subsecao_id` INT NOT NULL,
  `projeto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_caixa_selecao_subsecao1_idx` (`subsecao_id` ASC) VISIBLE,
  INDEX `fk_caixa_selecao_projeto1_idx` (`projeto_id` ASC) VISIBLE,
  CONSTRAINT `fk_caixa_selecao_subsecao1`
    FOREIGN KEY (`subsecao_id`)
    REFERENCES `gametect`.`subsecao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_caixa_selecao_projeto1`
    FOREIGN KEY (`projeto_id`)
    REFERENCES `gametect`.`projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gametect`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gametect`.`item` (
  `caixa_selecao_id` INT NOT NULL,
  `selecionado` TINYINT NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`caixa_selecao_id`, `descricao`),
  CONSTRAINT `fk_item_caixa_selecao1`
    FOREIGN KEY (`caixa_selecao_id`)
    REFERENCES `gametect`.`caixa_selecao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
