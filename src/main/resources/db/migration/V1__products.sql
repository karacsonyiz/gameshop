create table products (
product_id varchar(100) unique NOT NULL,
name varchar(100) NOT NULL,
producer varchar(100) NOT NULL,
price bigint NOT NULL,
quantity bigint NOT NULL,
constraint pk_products primary key(product_id));

 insert into products(product_id, name, producer, price, quantity)
     values ("kH34Ju", "Age of Empires II: The Age of Kings", "Ensemble Studios", 3200, 32),
     ("jH34Ju", "Age of Mythology", "Ensemble Studios", 4000, 4),
     ("tH34Ju", "Age of Empires II: The Conquerors", "Ensemble Studios", 3000, 3),
     ("kH32Je", "Total War: Warhammer", "Creative Assembly", 3500, 7),
     ("kH94Jc", "Carmageddon 2", "Argonaut Games", 4000, 10),
     ("kH14Jh", "Playerunknown's Battlegrounds", "Bluehole", 4300, 6),
     ("tH34Js", "World of Warcraft", "Blizzard", 3600, 15),
     ("jH34Jk", "Grand Theft Auto: Vice City", "Rockstar Games", 3000, 12),
     ("eH34Jd", "Warcraft 3 Remastered", "Blizzard", 3800, 0),
     ("tH34Jl", "Witch 3: Wild Hunt", "Triumph Studios", 4000, 10),
     ("kH34Ji", "The Elder Scrolls V: Skyrim", "Bathesda Games", 2700, 3),
     ("kH94Ju", "Dark Souls 3", "From Software" ,8000 ,2),
     ("vH34Ju","Settlers 3","Blue Byte", 2300,4);
