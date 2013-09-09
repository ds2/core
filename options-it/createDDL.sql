CREATE TABLE core_options (ID BIGINT NOT NULL, app_name VARCHAR(255), value VARCHAR(3000), encrypted TINYINT(1) default 0 NOT NULL, modified_by VARCHAR(255), name VARCHAR(255) NOT NULL, created DATETIME NOT NULL, modified DATETIME NOT NULL, stage INTEGER, value_type INTEGER, PRIMARY KEY (ID))
ALTER TABLE core_options ADD CONSTRAINT UNQ_core_options_0 UNIQUE (app_name, name)
CREATE TABLE core_id (table_name VARCHAR(50) NOT NULL, core_options DECIMAL(38), PRIMARY KEY (table_name))
INSERT INTO core_id(table_name, core_options) values ('next_id', 0)
