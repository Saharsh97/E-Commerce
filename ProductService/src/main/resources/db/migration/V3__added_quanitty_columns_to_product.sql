ALTER TABLE product
    ADD quantity_available INT NULL;

ALTER TABLE product
    ADD quantity_sold INT NULL;

UPDATE product
SET quantity_available = 100
WHERE quantity_available IS NULL;
