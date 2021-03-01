USE HotelDB;

-- INT, CHAR, CHAR, INT, INT, DECIMAL, DECIMAL
INSERT INTO RoomType (RoomType, ADA, StandardOccupancy, MaxOccupancy, BasePrice, ExtraPerson)
VALUES ('Double', 'No', 2, 4, 199.99, 10.00),
('Double', 'Yes', 2, 4, 174.99, 10.00),
('Double', 'No', 2, 4, 199.99, 10.00),
('Double', 'Yes', 2, 4, 174.99, 10.00),
('Single', 'No', 2, 2, 174.99, 0.00),
('Single', 'Yes', 2, 2, 149.99, 0.00),
('Single', 'No', 2, 2, 174.99, 0.00),
('Single', 'Yes', 2, 2, 149.99, 0.00),
('Double', 'No', 2, 4, 199.99, 10.00),
('Double', 'Yes', 2, 4, 174.99, 10.00),
('Double', 'No', 2, 4, 199.99, 10.00),
('Double', 'Yes', 2, 4, 174.99, 10.00),
('Single', 'No', 2, 2, 174.99, 0.00),
('Single', 'Yes', 2, 2, 149.99, 0.00),
('Single', 'No', 2, 2, 174.99, 0.00),
('Single', 'Yes', 2, 2, 149.99, 0.00),
('Suite', 'Yes', 3, 8, 399.99, 20.00),
('Suite', 'Yes', 3, 8, 399.99, 20.00);

-- INT, CHAR, CHAR, CHAR, CHAR
INSERT INTO Address (Address, City, State, Zip)
VALUES ('12345 Apple Lane', 'San Diego', 'CA', '92124'),
('379 Old Shore Street', 'Council Bluffs', 'IA', '51501'),
('750 Wintergreen Dr.', 'Wasilla', 'AK', '99654'),
('9662 Foxrun Lane', 'Harlingen', 'TX', '78552'),
('9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096'),
('762 Wild Rose Street', 'Saginaw', 'MI', '48601'),
('7 Poplar Dr.', 'Arvada', 'CO', '80003'),
('70 Oakwood St.', 'Zion', 'IL', '60099'),
('7556 Arrowhead St.', 'Cumberland', 'RI', '02864'),
('77 West Surrey Street', 'Oswego', 'NY', '13126'),
('939 Linda Rd.', 'Burke', 'VA', '22015'),
('87 Queen St.', 'Drexel Hill', 'PA', '19026');

-- INT, CHAR
INSERT INTO Amenities (AmenitiesName)
VALUES ('Microwave'), 
('Jacuzzi'), 
('Refrigerator'), 
('Oven');

-- INT, CHAR
INSERT INTO Room (RoomNumber, RoomTypeId)
VALUES (201, 1), (202, 2), (203, 3), (204, 4), (205, 5), (206, 6),
(207, 7), (208, 8), (301, 9), (302, 10), (303, 11), (304, 12), (305, 13),
(306, 14), (307, 15), (308, 16), (401, 17), (402, 18);

-- INT, INT
INSERT INTO RoomAmenities (RoomNumber, AmenitiesID)
VALUES (201, 1), (201, 2), (202, 3), (203, 1), (203, 2), (204, 3), (205, 1), (205, 2), (205, 3), (206, 1),
(206, 3), (207, 1), (207, 2), (207, 3), (208, 1), (208, 3), (301, 1), (301, 2), (302, 3), (303, 1), 
(303, 2), (304, 3), (305, 1), (305, 2), (305, 3), (306, 1), (306, 3), (307, 1), (307, 2), (307, 3), 
(308, 1), (308, 3), (401, 1), (401, 3), (401, 4), (402, 1), (402, 3), (402, 4);

-- CHAR, CHAR, INT, CHAR
INSERT INTO Guest (FirstName, LastName, AddressId, Phone)
VALUES ('Veronica', 'McCormick', 1, '(619)813-2184'),
('Mack', 'Simmer', 2, '(291) 553-0508'),
('Bettyann', 'Seery', 3, '(478) 277-9632'),
('Duane', 'Cullison', 4, '(308) 494-0198'),
('Karie', 'Yang', 5, '(214) 730-0298'),
('Aurore', 'Lipton', 6, '(377) 507-0974'),
('Zachery', 'Luechtefeld', 7, '(814) 485-2615'),
('Jeremiah', 'Pendergrass', 8, '(279) 491-0960'),
('Walter', 'Holaway', 9, '(446) 396-6785'),
('Wilfred', 'Vise', 10, '(834) 727-1001'),
('Maritza', 'Tilton', 11, '(446) 351-6860'),
('Joleen', 'Tison', 12, '(231) 893-2755');

-- INT, INT, INT, DATE, DATE, DECIMAL
INSERT INTO Reservation (GuestId, Adults, Children, StartDate, EndDate, TotalCost)
VALUES (2, 1, 0, '2023/02/02', '2023/02/04', 299.98),
(3, 2, 1, '2023/02/05', '2023/02/10', 999.95),
(4, 2, 0, '2023/02/22', '2023/02/24', 349.98),
(5, 2, 2, '2023/03/06', '2023/03/07', 199.99),
(1, 1, 1, '2023/03/17', '2023/03/20', 524.97),
(6, 3, 0, '2023/03/18', '2023/03/23', 924.95),
(7, 2, 2, '2023/03/29', '2023/03/31', 349.98),
(8, 2, 0, '2023/03/31', '2023/04/05', 874.95),
(9, 1, 0, '2023/04/09', '2023/04/13', 799.96),
(10, 1, 1, '2023/04/23', '2023/04/24', 174.99),
(11, 2, 4, '2023/05/30', '2023/06/02', 1199.97),
(12, 2, 0, '2023/06/10', '2023/06/14', 599.96),
(12, 1, 0, '2023/06/10', '2023/06/14', 599.96),
(6, 3, 0, '2023/06/17', '2023/06/18', 184.99),
(1, 2, 0, '2023/06/28', '2023/07/02', 699.96),
(9, 3, 1, '2023/07/13', '2023/07/14', 184.99),
(10, 4, 2, '2023/07/18', '2023/07/21', 1259.97),
(3, 2, 1, '2023/07/28', '2023/07/29', 199.99),
(3, 1, 0, '2023/08/30', '2023/09/01', 349.98),
(2, 2, 0, '2023/09/16', '2023/09/17', 149.99),
(5, 2, 2, '2023/09/13', '2023/09/15', 399.98),
(4, 2, 2, '2023/11/22', '2023/11/25', 1199.97),
(2, 2, 0, '2023/11/22', '2023/11/25', 449.97),
(2, 2, 2, '2023/11/22', '2023/11/25', 599.97),
(11, 2, 0, '2023/12/24', '2023/12/28', 699.96);

-- INT, INT
INSERT INTO RoomReservation (RoomNumber, ReservationId)
VALUES (308, 1), (203, 2), (305, 3), (201, 4), (307, 5), (302, 6),
(202, 7), (304, 8), (301, 9), (207, 10), (401, 11), (206, 12), (208, 13), 
(304, 14), (205, 15), (204, 16), (401, 17), (303, 18), (305, 19), (208, 20),
(203, 21), (401, 22), (206, 23), (301, 24), (302, 25);

SET SQL_SAFE_UPDATES = 0;

DELETE FROM RoomReservation
WHERE ReservationId = 8 AND RoomNumber = 304;

DELETE FROM Reservation
WHERE ReservationId = 8;

DELETE FROM Guest
WHERE GuestId = 8;

DELETE FROM Address
WHERE AddressId = 8;


SET SQL_SAFE_UPDATES = 1;
