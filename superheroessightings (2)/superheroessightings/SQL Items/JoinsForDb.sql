USE superheroesSightings;

SELECT superHumans.superHumanName as 'Super Hero/Villian', location.locationName
FROM superHumans
JOIN sighting on superHumans.superHumanId = sighting.superHumanId
JOIN location on sighting.locationId = location.locationId
WHERE location.locationId = 3;

SELECT location.locationName, sighting.sightingDate
FROM location
JOIN sighting on location.locationId = sighting.locationId
JOIN superHumans on sighting.superHumanId = superHumans.superHumanId
WHERE superHumans.superHumanId = 1;

SELECT superHumanName, locationName, sightingDate
FROM sighting
JOIN location on sighting.locationId = location.locationId
JOIN superHumans on sighting.superHumanId = superHumans.superHumanId
WHERE sightingDate = '2020-02-17';

SELECT superHumans.superHumanName, organizations.organizationName
FROM superHumans
JOIN superHumansOrganization on superHumans.superHumanId = superHumansOrganization.superHumanId
JOIN organizations on superHumansOrganization.organizationId = organizations.organizationId
WHERE organizations.organizationId = 1;

SELECT superHumans.superHumanName, organizations.organizationName
FROM organizations
JOIN superHumansOrganization on organizations.organizationId = superHumansOrganization.organizationId
JOIN superHumans on superHumans.superHumanId = superHumansOrganization.superHumanId
WHERE superHumans.superHumanId = 1;