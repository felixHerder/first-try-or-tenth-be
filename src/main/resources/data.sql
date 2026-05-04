INSERT INTO profiles (uuid, created_at, edited_at, name, phone_number, address, image_url, deleted)
VALUES ('58217387-9b24-4f51-8742-19e342790100', '2026-01-05 09:15:22Z', '2026-01-05 09:15:22Z', 'Marcus Miller',
        '555-0199', '101 Alpine Way', 'http://img.local/p1.png', false),
       ('9d3e8612-429a-4e2a-9f8a-552319283741', '2026-01-06 10:20:00Z', '2026-01-06 10:20:00Z', 'Sarah Jenkins',
        '555-0288', '202 Forest Rd', 'http://img.local/p2.png', false),
       ('7f2a11b3-d4e5-4f6c-8a90-1234567890ab', '2026-01-10 14:05:10Z', '2026-01-10 14:05:10Z', 'David Chen',
        '555-0377', '303 Harbor Dr', 'http://img.local/p3.png', false),
       ('22334455-6677-8899-aabb-ccddeeff0011', '2026-01-15 11:30:45Z', '2026-01-15 11:30:45Z', 'Emma Wilson',
        '555-0466', '404 Skyline Blvd', 'http://img.local/p4.png', false),
       ('ffeeddcc-bbaa-9988-7766-554433221100', '2026-01-20 16:45:30Z', '2026-01-20 16:45:30Z', 'Lucas Hedges',
        '555-0555', '505 Meadow Ln', 'http://img.local/p5.png', false),
       ('gghhddcc-bbaa-9988-7766-554433221100', '2026-01-20 16:45:30Z', '2026-01-20 16:45:30Z', 'Bob Admin',
        '999-0555', 'Town center 23', null, false),
       ('a0112233-4455-6677-8899-00aabbccdd11', '2026-05-04 10:00:00Z', '2026-05-04 10:00:00Z', 'Sophia Martinez',
        '555-0611', '606 Oak St', 'http://img.local/p6.png', false),
       ('b0112233-4455-6677-8899-00aabbccdd22', '2026-05-04 10:05:00Z', '2026-05-04 10:05:00Z', 'James Taylor',
        '555-0722', '707 Pine Ln', 'http://img.local/p7.png', false),
       ('c0112233-4455-6677-8899-00aabbccdd33', '2026-05-04 10:10:00Z', '2026-05-04 10:10:00Z', 'Linda Moore',
        '555-0833', '808 Birch Rd', 'http://img.local/p8.png', false),
       ('d0112233-4455-6677-8899-00aabbccdd44', '2026-05-04 10:15:00Z', '2026-05-04 10:15:00Z', 'Michael Brown',
        '555-0944', '909 Cedar Ave', 'http://img.local/p9.png', false),
       ('e0112233-4455-6677-8899-00aabbccdd55', '2026-05-04 10:20:00Z', '2026-05-04 10:20:00Z', 'Isabella Garcia',
        '555-1055', '111 Walnut Ct', 'http://img.local/p10.png', false);

INSERT INTO vehicles (uuid, created_at, edited_at, make, model, year, license_plate, color, engine_type,
                      fuel_type, transmission_type, deleted)
VALUES ('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d', '2026-01-01 08:00:00Z', '2026-01-01 08:00:00Z', 'Volkswagen',
        'Golf GTI', 2021,
        'WOB-VW12', 'Tornado Red', 10, 10, 20, false),
       ('b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e', '2026-01-01 09:00:00Z', '2026-01-01 09:00:00Z', 'BMW', '320d', 2022,
        'M-XY-554', 'Jet Black', 10, 20, 10, false),
       ('c3d4e5f6-a7b8-9c0d-1e2f-3a4b5c6d7e8f', '2026-01-02 10:30:00Z', '2026-01-02 10:30:00Z', 'Toyota', 'Prius', 2023,
        '7HBY-990', 'Pearl White', 20, 10, 10, false),
       ('d4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f9a', '2026-01-03 12:45:00Z', '2026-01-03 12:45:00Z', 'Volkswagen', 'ID.4',
        2024,
        'WOB-ID40', 'Blue Dusk', 30, 30, 10, false),
       ('e5f6a7b8-c9d0-1e2f-3a4b-5c6d7e8f9a0b', '2026-01-04 15:20:00Z', '2026-01-04 15:20:00Z', 'Honda', 'Civic', 2022,
        'HND-2022', 'Modern Steel', 10, 10, 10, false);

INSERT INTO instructors (uuid, created_at, edited_at, profile_uuid, deleted)
VALUES ('11aa22bb-33cc-44dd-55ee-66ff77889900', '2026-01-05 10:00:00Z', '2026-01-05 10:00:00Z',
        '58217387-9b24-4f51-8742-19e342790100', false),
       ('22bb33cc-44dd-55ee-66ff-77889900aa11', '2026-01-06 11:00:00Z', '2026-01-06 11:00:00Z',
        '9d3e8612-429a-4e2a-9f8a-552319283741', false),
       ('33cc44dd-1111-2222-3333-444455556666', '2026-05-04 11:00:00Z', '2026-05-04 11:00:00Z',
        'a0112233-4455-6677-8899-00aabbccdd11', false),
       ('44dd55ee-2222-3333-4444-555566667777', '2026-05-04 11:05:00Z', '2026-05-04 11:05:00Z',
        'b0112233-4455-6677-8899-00aabbccdd22', false),
       ('55ee66ff-3333-4444-5555-666677778888', '2026-05-04 11:10:00Z', '2026-05-04 11:10:00Z',
        'c0112233-4455-6677-8899-00aabbccdd33', false),
       ('66ff7788-4444-5555-6666-777788889999', '2026-05-04 11:15:00Z', '2026-05-04 11:15:00Z',
        'd0112233-4455-6677-8899-00aabbccdd44', false),
       ('77889900-5555-6666-7777-888899990000', '2026-05-04 11:20:00Z', '2026-05-04 11:20:00Z',
        'e0112233-4455-6677-8899-00aabbccdd55', false);

INSERT INTO instructors_vehicles (instructor_uuid, vehicle_uuid)
VALUES ('11aa22bb-33cc-44dd-55ee-66ff77889900', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d'),
       ('22bb33cc-44dd-55ee-66ff-77889900aa11', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d'),
       ('33cc44dd-1111-2222-3333-444455556666', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d'),
       ('44dd55ee-2222-3333-4444-555566667777', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d'),
       ('55ee66ff-3333-4444-5555-666677778888', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d'),
       ('66ff7788-4444-5555-6666-777788889999', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d'),
       ('11aa22bb-33cc-44dd-55ee-66ff77889900', 'd4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f9a'),
       ('22bb33cc-44dd-55ee-66ff-77889900aa11', 'b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e');


INSERT INTO trainees (uuid, created_at, edited_at, profile_uuid, instructor_uuid, vehicle_uuid, deleted)
VALUES ('33cc44dd-55ee-66ff-7788-9900aa11bb22', '2026-01-11 09:00:00Z', '2026-01-11 09:00:00Z',
        '7f2a11b3-d4e5-4f6c-8a90-1234567890ab', '11aa22bb-33cc-44dd-55ee-66ff77889900',
        'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d', false),
       ('44dd55ee-66ff-7788-9900-aa11bb22cc33', '2026-01-16 14:20:00Z', '2026-01-16 14:20:00Z',
        '22334455-6677-8899-aabb-ccddeeff0011', '11aa22bb-33cc-44dd-55ee-66ff77889900',
        'd4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f9a', false),
       ('55ee66ff-7788-9900-aa11-bb22cc33dd44', '2026-01-21 10:15:00Z', '2026-01-21 10:15:00Z',
        'ffeeddcc-bbaa-9988-7766-554433221100', '22bb33cc-44dd-55ee-66ff-77889900aa11',
        'b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e', false);

INSERT INTO sessions (uuid, created_at, edited_at, scheduled_at, trainee_uuid, instructor_uuid, vehicle_uuid, deleted)
VALUES ('66ff7788-9900-aa11-bb22-cc33dd44ee55', '2026-02-01 08:30:00Z', '2026-02-01 08:30:00Z', '2026-04-10 09:00:00Z',
        '33cc44dd-55ee-66ff-7788-9900aa11bb22', '11aa22bb-33cc-44dd-55ee-66ff77889900',
        'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d', false),
       ('77889900-aa11-bb22-cc33-dd44ee55ff66', '2026-02-02 11:00:00Z', '2026-02-02 11:00:00Z', '2026-04-11 13:00:00Z',
        '44dd55ee-66ff-7788-9900-aa11bb22cc33', '11aa22bb-33cc-44dd-55ee-66ff77889900',
        'd4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f9a', false),
       ('889900aa-11bb-22cc-33dd-44ee55ff6677', '2026-02-03 15:45:00Z', '2026-02-03 15:45:00Z', '2026-04-12 10:30:00Z',
        '55ee66ff-7788-9900-aa11-bb22cc33dd44', '22bb33cc-44dd-55ee-66ff-77889900aa11',
        'b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e', false);

-- username: admin, password: ftotadmin
INSERT INTO users (uuid, created_at, edited_at, profile_uuid, username, password, role, deleted)
VALUES ('11aa22bb-33cc-44dd-55ee-66ff77889900', '2026-02-01 08:30:00Z', '2026-02-01 08:30:00Z',
        'gghhddcc-bbaa-9988-7766-554433221100',
        'admin', '$2y$10$zmGEY1NTY3FjWj9o.A/MduUuA4.ThUPElQ9Z.B3Yna1yJe5GWdjem', 'ROLE_ADMIN', false);