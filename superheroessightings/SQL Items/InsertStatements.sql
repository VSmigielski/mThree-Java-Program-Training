USE superheroesSightings;

INSERT INTO powers(powerId, superpower)
VALUES(1, "Mind-control"),
		(2, "Telekinesis"),
        (3, "Pyrokinesis"),
        (4, "Chrono-manipulation"),
        (5, "Electrokinesis"),
        (6, "Speed");

INSERT INTO organizations(organizationId, organizationName, organizationDescription, address, contactInfo)
VALUES(1, "Avengers", "wE aVeNgE", "111 Avenge Ave. Unit A-111 90000", "NO."),
		(2, "Ultron", "wE ultrons", "222 Ugh Ave. Unit B-111 90000", "NOO."),
        (3, "Disney", "wE princesses", "333 Avenge Ave. Unit C-111 90000", "NOoo."),
        (4, "Marvel", "wE own Avengers", "444 Avenge Ave. Unit D-111 90000", "NOoooo."),
        (5, "Spooders", "wE spiders", "555 Avenge Ave. Unit E-111 90000", "NOooooo."),
        (6, "Mutant Ninja Turtles", "wE live in sewers", "665 Avenge Ave. Unit F-111 90000", "NOoooooo.");

INSERT INTO location(locationId, locationName, locationDescription, address, latitude, longitude)
VALUES(1, "Square", "Everything is Square here", "111 Square Ave. Unit A-111 90000", 1.1, 1.2),
    (2, "Circle", "Everything is Circle here", "111 Circle Ave. Unit A-111 90000", 2.1, 2.2),
    (3, "Triangle", "Everything is Triangle here", "111 Triangle Ave. Unit A-111 90000", 3.1, 3.2);
 
 INSERT INTO superHumans(superHumanId, superHumanName, superDescription, powerId)
VALUES(1, "BIG BRAIN", "big head", 1),
		(2, "BIG HAND", "big palm", 2),
        (3, "BIG GAS", "big glutes", 3),
        (4, "BIG AMYGDALLA", "big physics", 4),
        (5, "BIG MAGNET", "big electro-spectrum", 5),
        (6, "BIG METABOLISM", "big legs", 6),
        (7, "BIG BRAIN2", "big head2", 1),
        (8, "BIG BRAIN3", "big head3", 1),
        (9, "BIG HAND2", "big palm2", 2);
 INSERT INTO superHumansOrganization(shoId, superHumanId, organizationId)
VALUES(1, 1, 3),
		(2, 2, 1),
		(3, 3, 6),
        (4, 4, 1),
        (5, 5, 4),
        (6, 6, 4),
        (7, 7, 3),
        (8, 8, 3),
        (9, 8, 1),
        (10, 1, 1);
 INSERT INTO sighting(shlId, superHumanId, locationId, sightingDate)
	VALUES(1, 1, 1, '2020-01-01'),
    (2, 2, 1, '2020-01-01'),
    (3, 3, 1, '2020-01-01'),
    (4, 4, 2, '2020-02-12'),
    (5, 5, 2, '2020-01-01'),
    (6, 6, 3, '2021-01-01'),
    (7, 7, 1, '2021-01-01'),
    (8, 8, 3, '2022-11-01'),
    (9, 9, 3, '2010-01-01'),
    (10, 1, 2, '2021-01-01'),
    (11, 1, 3, '2022-02-02');