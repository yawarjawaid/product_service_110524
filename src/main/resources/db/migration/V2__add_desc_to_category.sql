ALTER TABLE category
    ADD `description` VARCHAR(255) NULL;

ALTER TABLE category
    DROP COLUMN iddeleted;

ALTER TABLE category
    DROP COLUMN isdeleted;

ALTER TABLE product
    DROP COLUMN iddeleted;