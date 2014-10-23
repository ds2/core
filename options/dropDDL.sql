ALTER TABLE core_options DROP CONSTRAINT UNQ_core_options_0
ALTER TABLE core_optionvalues DROP CONSTRAINT FK_core_optionvalues_ref_option
ALTER TABLE core_optionvalues DROP CONSTRAINT UNQ_core_optionvalues_0
DROP TABLE core_options CASCADE
DROP TABLE core_ctx CASCADE
DROP TABLE core_optionvalues CASCADE
DELETE FROM core_id WHERE table_name = 'core_optionvalues'
DELETE FROM core_id WHERE table_name = 'core_options'
