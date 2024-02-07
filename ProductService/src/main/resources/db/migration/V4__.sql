ALTER TABLE category
    ADD is_popular BIT(1) NULL;

UPDATE category
SET is_popular = 0
WHERE is_popular IS NULL;
ALTER TABLE category
    MODIFY is_popular BIT (1) NOT NULL;

UPDATE category
SET dynamic_pricing = '0'
WHERE dynamic_pricing IS NULL;
ALTER TABLE category
    MODIFY dynamic_pricing DOUBLE NOT NULL;

UPDATE product
SET quantity_sold = '0'
WHERE quantity_sold IS NULL;
ALTER TABLE product
    MODIFY quantity_sold DOUBLE NOT NULL;