INSERT INTO vehicle (
    uuid,
    model,
    make,
    year,
    license_plate,
    color,
    engine_type_id,
    fuel_type_id,
    transmission_type_id,
    image_url
) VALUES
-- 1. Electric Tesla Model 3
(gen_random_uuid(), 'Model 3', 'Tesla', 2023, 'EL-888-TX', 'White', 30, 30, 10, 'https://images.example.com/tesla3.jpg'),

-- 2. Hybrid Toyota RAV4
(gen_random_uuid(), 'RAV4', 'Toyota', 2022, 'HB-452-TY', 'Silver', 20, 10, 10, 'https://images.example.com/rav4.jpg'),

-- 3. Internal Combustion Ford F-150
(gen_random_uuid(), 'F-150', 'Ford', 2021, 'RD-112-FD', 'Black', 10, 10, 10, 'https://images.example.com/f150.jpg'),

-- 4. Electric Rivian R1T
(gen_random_uuid(), 'R1T', 'Rivian', 2024, 'RV-900-RT', 'Blue', 30, 30, 10, 'https://images.example.com/r1t.jpg'),

-- 5. Diesel Volkswagen Golf (Manual)
(gen_random_uuid(), 'Golf', 'Volkswagen', 2019, 'DE-773-VW', 'Red', 10, 20, 20, 'https://images.example.com/golf.jpg'),

-- 6. Hybrid Honda CR-V
(gen_random_uuid(), 'CR-V', 'Honda', 2023, 'HN-221-CR', 'Grey', 20, 10, 10, NULL);