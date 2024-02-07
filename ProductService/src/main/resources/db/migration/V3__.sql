ALTER TABLE product
    ADD quantity_available INT NULL;

ALTER TABLE product
    ADD quantity_sold INT NULL;

UPDATE product
SET quantity_available = '100'
WHERE quantity_available IS NULL;
ALTER TABLE product
    MODIFY quantity_available INT NOT NULL;

UPDATE product
SET quantity_sold = '0'
WHERE quantity_sold IS NULL;
ALTER TABLE product
    MODIFY quantity_sold INT NOT NULL;