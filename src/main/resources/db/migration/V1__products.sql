create table products (
product_id varchar(100) unique NOT NULL,
name varchar(100) NOT NULL,
producer varchar(100) NOT NULL,
price bigint NOT NULL,
quantity bigint NOT NULL,
constraint pk_products primary key(product_id));

 insert into products(product_id, name, producer, price, quantity)
     values ("0765ec13-bdd4-4ff1-8b41-0e416c8f72e7", "Age of Empires II: The Age of Kings", "Ensemble Studios", 3200, 32),
     ("36b2b83d-7325-4024-8832-c6e702ac66eb ", "Age of Mythology", "Ensemble Studios", 4000, 4),
     ("505c5dae-ad80-4a64-8897-1310a19ac362", "Age of Empires II: The Conquerors", "Ensemble Studios", 3000, 3),
     ("aa566089-e44e-40dd-971e-da0ffc71c0ac", "Total War: Warhammer", "Creative Assembly", 3500, 7),
     ("3b3a8fda-16c3-4b88-892d-5f64146ff707", "Carmageddon 2", "Argonaut Games", 4000, 10),
     ("e2cb46b9-555b-447d-bfe5-287bb2fdc7a1", "Playerunknown's Battlegrounds", "Bluehole", 4300, 6),
     ("2f98eaad-b491-40c8-ae18-a25ad0d7c263", "World of Warcraft", "Blizzard", 3600, 15),
     ("9a454e4c-e911-46d9-a593-13b7cefcf939", "Grand Theft Auto: Vice City", "Rockstar Games", 3000, 12),
     ("6300ba9c-1569-4b28-99e7-4b3ddfed7a98", "Warcraft 3 Remastered", "Blizzard", 3800, 0),
     ("2bbb8d2a-1571-4920-a909-dcf758e90e60", "Witch 3: Wild Hunt", "Triumph Studios", 4000, 10),
     ("c0c63d01-58ab-41bf-96b1-6d602c06a18c", "The Elder Scrolls V: Skyrim", "Bathesda Games", 2700, 3),
     ("c07a99ea-28be-4e13-acb2-3e0d615315c2", "Dark Souls 3", "From Software" ,8000 ,2),
     ("a7376df4-6e63-4635-bf76-4505ebf5aeb3","Settlers 3","Blue Byte", 2300,4);
