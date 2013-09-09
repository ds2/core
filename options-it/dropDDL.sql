ALTER TABLE core_options DROP FOREIGN KEY UNQ_core_options_0
DROP TABLE core_options
DELETE FROM core_id WHERE table_name = 'core_options'
