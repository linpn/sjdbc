CREATE SCHEMA IF NOT EXISTS `slave_0`;

CREATE TABLE IF NOT EXISTS `t_order_0` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_1` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_2` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_3` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_4` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_5` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_6` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_7` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_8` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_9` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_0` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_1` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_2` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_3` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_4` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_5` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_6` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_7` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_8` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_order_item_9` (`item_id` INT NOT NULL, `order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`item_id`));
CREATE TABLE IF NOT EXISTS `t_config` (`id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `t_global` (`id` INT NOT NULL, `status` VARCHAR(45) NULL, PRIMARY KEY (`id`));
