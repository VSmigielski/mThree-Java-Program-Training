USE HotelDB;

-- 1. Write a query that returns a list of reservations that end in July 2023, 
-- including the name of the guest, the room number(s), and the reservation 
-- dates.

SELECT DISTINCT `FirstName`, `LastName`, `RoomNumber`, `EndDate`
FROM Guest
INNER JOIN Reservation R ON R.GuestId = guest.GuestId 
INNER JOIN RoomReservation RR ON RR.ReservationId = R.ReservationId
WHERE R.EndDate BETWEEN '2023/07/01' AND '2023/07/31';

-- FirstName LastName RoomNumber EndDate
-- Veronica McCormick 205        2023-07-02
-- Walter Holway      204        2023-07-14
-- Wilfred Vise       401        2023-07-21
-- Bettyann Seery     303        2023-07-29

-- 2. Write a query that returns a list of all reservations for rooms with a 
-- jacuzzi, displaying the guest's name, the room number, and the dates of the 
-- reservation.

SELECT DISTINCT `FirstName`, `LastName`, RM.`RoomNumber`, `StartDate`, `EndDate`
FROM Guest
INNER JOIN Reservation R ON R.GuestId = guest.GuestId 
INNER JOIN RoomReservation RR ON RR.ReservationId = R.ReservationId
INNER JOIN Room RM ON RM.RoomNumber = RR.RoomNumber
INNER JOIN RoomAmenities RA ON RA.RoomNumber = RM.RoomNumber
INNER JOIN Amenities A ON A.AmenitiesID = RA.AmenitiesID
WHERE A.AmenitiesID = 1 OR A.AmenitiesID = 3;

-- FirstName LastName RoomNumber StartDate  EndDate
-- Karie Yang         201        2023-03-06 2023-03-07
-- Bettyann Seery     203        2023-02-05 2023-02-10
-- Karie Yang	      203        2023-09-13 2023-09-15
-- Walter Holoway     301        2023-04-09 2023-04-13
-- Mack Simmer        301        2023-11-22 2023-11-25
-- Bettyann Seery	  303        2023-07-22 2023-07-29
-- Veronica McCormick 205        2023-06-28 2023-07-02
-- Wilfred Vise       207        2023-04-23 2023-04-24
-- Duan Cullison      305        2023-02-22 2023-02-24
-- Bettyann Seery     305        2023-08-30 2023-09-01
-- Veronica McCormick 307        2023-03-17 2023-03-20

-- 3. Write a query that returns all the rooms reserved for a specific guest, 
-- including the guest's name, the room(s) reserved, the starting date of the 
-- reservation, and how many people were included in the reservation. 
-- (Choose a guest's name from the existing data.)

SELECT DISTINCT `FirstName`, `LastName`, RM.`RoomNumber`, `StartDate`, `Adults`, `Children`
FROM Guest
INNER JOIN Reservation R ON R.GuestId = guest.GuestId 
INNER JOIN RoomReservation RR ON RR.ReservationId = R.ReservationId
INNER JOIN Room RM ON RM.RoomNumber = RR.RoomNumber
WHERE guest.FirstName = 'Veronica' AND guest.LastName = 'McCormick';

-- FirstName LastName RoomNumber StartDate Adults Children
-- Veronica McCormick 307        2023-03-17 1     1
-- Veronica McCormick 205        2023-06-28 2     0

-- 4. Write a query that returns a list of rooms, reservation ID, and per-room 
-- cost for each reservation. The results should include all rooms, whether 
-- or not there is a reservation associated with the room.

SELECT DISTINCT RM.`RoomNumber`, RR.`ReservationId`, `BasePrice`, `TotalCost`
FROM RoomType
INNER JOIN Room RM ON RM.RoomTypeId = RoomType.RoomTypeId
LEFT JOIN RoomReservation RR ON RR.RoomNumber = RM.RoomNumber
LEFT JOIN Reservation R ON R.ReservationId = RR.ReservationId;

-- RoomNumber ReservationId BasePrice TotalCost
-- 201 4 199.99 199.99
-- 202 7 174.99 349.98
-- 203 2 199.99 999.95
-- 203 21 199.99 399.98
-- 204 16 174.99 184.99
-- 205 15 174.99 699.96
-- 206 12 149.99 599.96
-- 206 23 149.99 449.97
-- 207 10 174.99 174.99
-- 208 13 149.99 599.96
-- 208 20 149.99 149.99
-- 301 9 199.99 799.96
-- 301 24 199.99 599.97
-- 302 6 174.99 924.95
-- 302 25 174.99 699.96
-- 303 18 199.99 199.99
-- 304 14 174.99 184.99
-- 305 3 174.99 349.98
-- 306 NULL 149.99 NULL
-- 307 5 174.99 524.97
-- 308 1 149.99 299.98
-- 401 11 399.99 1199.97
-- 401 17 399.99 1259.97
-- 401 22 399.99 1199.97
-- 402 NULL 399.99 NULL


-- 5. Write a query that returns all the rooms accommodating at least three 
-- guests and that are reserved on any date in April 2023.

SELECT DISTINCT `FirstName`, `LastName`, RM.`RoomNumber`, SUM(`Adults` + `Children`) AS `GuestCount`, `StartDate`, `EndDate`
FROM Guest
INNER JOIN Reservation R ON R.GuestId = guest.GuestId 
INNER JOIN RoomReservation RR ON RR.ReservationId = R.ReservationId
INNER JOIN Room RM ON RM.RoomNumber = RR.RoomNumber
WHERE (R.EndDate BETWEEN '2023/04/01' AND '2023/04/31') 
AND (R.StartDate BETWEEN '2023/04/01' AND '2023/04/31')
HAVING SUM(`Adults`+ `Children`) >= 3;

-- FirstName LastName RoomNumber StartDate EndDate
-- 0 rows fetched

-- 6. Write a query that returns a list of all guest names and the number of 
-- reservations per guest, sorted starting with the guest with the most 
-- reservations and then by the guest's last name.

SELECT DISTINCT guest.`FirstName`, guest.`LastName`, COUNT(R.`ReservationId`) AS `ReservationCount`
FROM Guest
INNER JOIN Reservation R ON R.GuestId = guest.GuestId 
GROUP BY R.ReservationId, guest.FirstName, guest.LastName
ORDER BY COUNT(ReservationCount) AND guest.`LastName`;

-- FirstName LastName ReservationCount
-- Veronica McCormick 1
-- Mack Simmer        1
-- Bettyann Seery     1
-- Duane Cullison     1
-- Karie Yang         1
-- Aurore Lipton      1
-- Zachery Luechtefeld 1
-- Walter Holaway     1
-- Wilfred Vise       1
-- Maritza Tilton     1
-- Joleen Tison       1



-- 7. Write a query that displays the name, address, and phone number of a 
-- guest based on their phone number. (Choose a phone number from the 
-- existing data.)
SELECT DISTINCT `FirstName`, `LastName`, `Address`, `State`, `Zip`, `Phone`
FROM Guest
INNER JOIN Address AD ON AD.AddressId = guest.AddressId 
WHERE guest.Phone = '(619)813-2184';