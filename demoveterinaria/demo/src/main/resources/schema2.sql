CREATE TABLE IF NOT EXISTS
    pet_adoption (
        id_requester INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),
        pet_type VARCHAR(255),
        description VARCHAR(255),
        telephone VARCHAR(10)
    );

INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Giancarlo', 'cat','I wanna adopt a cat','5550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Kelly', 'dog','I wanna adopt a dog please','7550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Diana', 'cat','I wanna adopt a cat','8550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Camila', 'dog','I wanna adopt a dog','9550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Alejandro', 'cat','I wanna adopt a cat','1550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Pedro', 'dog','I wanna adopt a dog','2550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Pablo', 'cat','I wanna adopt a cat','3550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Leon', 'dog','I wanna adopt a dog','4550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Jaramillo', 'dog','I wanna adopt a dog','6550011');
INSERT INTO pet_adoption (name, pet_type,description,telephone)
VALUES ('Arturo', 'dog','I wanna adopt a dog','3550011');
