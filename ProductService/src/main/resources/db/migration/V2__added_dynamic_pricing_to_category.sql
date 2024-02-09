ALTER TABLE category
    ADD dynamic_pricing DOUBLE NULL;

UPDATE category
SET dynamic_pricing = '0'
WHERE dynamic_pricing IS NULL;
ALTER TABLE category
    MODIFY dynamic_pricing DOUBLE NOT NULL;