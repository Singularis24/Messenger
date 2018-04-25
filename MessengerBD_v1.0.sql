CREATE TABLE `User` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`login` varchar NOT NULL UNIQUE,
	`password` varchar NOT NULL UNIQUE,
	`phone` varchar UNIQUE,
	`firstName` VARCHAR(255) NOT NULL,
	`lastName` VARCHAR(255) NOT NULL,
	`role` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Massage` (
	`id_message` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`id_user` INT NOT NULL,
	`content` varchar NOT NULL,
	`date` DATE NOT NULL,
	PRIMARY KEY (`id_message`)
);

CREATE TABLE `Dialog` (
	`id_user` INT NOT NULL,
	`id_message` INT NOT NULL UNIQUE,
	`createDate` DATE NOT NULL UNIQUE
);

ALTER TABLE `Massage` ADD CONSTRAINT `Massage_fk0` FOREIGN KEY (`id_user`) REFERENCES `User`(`id`);

ALTER TABLE `Dialog` ADD CONSTRAINT `Dialog_fk0` FOREIGN KEY (`id_user`) REFERENCES `User`(`id`);

ALTER TABLE `Dialog` ADD CONSTRAINT `Dialog_fk1` FOREIGN KEY (`id_message`) REFERENCES `Massage`(`id_message`);

