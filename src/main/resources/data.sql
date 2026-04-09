INSERT INTO vehicles (
    uuid,
    model,
    make,
    year,
    license_plate,
    color,
    engine_type_id,
    fuel_type_id,
    transmission_type_id,
    image_url,
    created_at,
    edited_at
) VALUES
-- 1. Electric Tesla Model 3
('550e8400-e29b-41d4-a716-446655440000', 'Model 3', 'Tesla', 2023, 'EL-888-TX', 'White', 30, 30, 10, 'https://images.example.com/tesla3.jpg',
 '2023-01-15 08:22:10', '2023-05-12 14:10:05'),

-- 2. Hybrid Toyota RAV4
('672e8400-e29b-41d4-a716-446655440001', 'RAV4', 'Toyota', 2022, 'HB-452-TY', 'Silver', 20, 10, 10, 'https://images.example.com/rav4.jpg',
 '2023-02-28 11:45:30', '2023-02-28 11:45:30'),

-- 3. Internal Combustion Ford F-150
('783e8400-e29b-41d4-a716-446655440002', 'F-150', 'Ford', 2021, 'RD-112-FD', 'Black', 10, 10, 10, 'https://images.example.com/f150.jpg',
 '2022-11-05 09:15:00', '2023-12-01 18:30:22'),

-- 4. Electric Rivian R1T
('894e8400-e29b-41d4-a716-446655440003', 'R1T', 'Rivian', 2024, 'RV-900-RT', 'Blue', 30, 30, 10, 'https://images.example.com/r1t.jpg',
 '2024-03-10 16:40:12', '2024-03-15 10:05:45'),

-- 5. Diesel Volkswagen Golf (Manual)
('905e8400-e29b-41d4-a716-446655440004', 'Golf', 'Volkswagen', 2019, 'DE-773-VW', 'Red', 10, 20, 20, 'https://images.example.com/golf.jpg',
 '2023-06-20 13:12:55', '2023-07-02 09:00:00'),

-- 6. Hybrid Honda CR-V
('016e8400-e29b-41d4-a716-446655440005', 'CR-V', 'Honda', 2023, 'HN-221-CR', 'Grey', 20, 10, 10, NULL,
 '2023-09-14 10:30:00', '2023-09-14 10:30:00');