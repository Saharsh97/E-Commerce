ALTER TABLE product
    ADD quantity_available INT NULL;

UPDATE product
SET quantity_available = '100'
WHERE quantity_available IS NULL;
ALTER TABLE product
    MODIFY quantity_available INT NOT NULL;