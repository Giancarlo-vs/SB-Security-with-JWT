CREATE TABLE IF NOT EXISTS
    pet_credit (
        id_pet INT AUTO_INCREMENT PRIMARY KEY,
        is_active BOOLEAN DEFAULT true,
        total_amount NUMERIC(8, 2) DEFAULT 0,
        amount_paid NUMERIC(8, 2),
        description VARCHAR(255)
    );
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 100.00, 0.00,'I wanna get a credit to buy a cow');
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 200.00, 0.00,'I wanna get a credit to buy a dog');
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 300.00, 0.00,'I wanna get a credit to buy a cat');
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 700.00, 0.00,'I wanna get a credit to buy a bear');
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 900.00, 0.00,'I wanna get a credit to buy a zebra');
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 1000.00, 0.00,'I wanna get a credit to buy a horse');
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 150.00, 0.00,'I wanna get a credit to buy a hamster');
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 100.00, 0.00,'I wanna get a credit to buy a hen');
INSERT INTO pet_credit (is_active, total_amount,amount_paid, description)
VALUES (true, 400.00, 0.00,'I wanna get a credit to buy a canary');
