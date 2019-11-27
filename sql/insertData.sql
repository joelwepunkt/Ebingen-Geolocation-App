insert into location(locationid, coordinates, title, description, address)
    VALUES (location_sequence.nextval,
            SDO_GEOMETRY(2001, 8307, SDO_POINT_TYPE(48.209142, 9.032517, null), null, null),
            'Hochschule Albstadt-Sigmaringen Win-Gebaeude',
            'Das Win-Gebaeude der Hochschule Albstadt-Sigmaringen zeichnet sich aus durch ...',
            'Johannesstrasse 3');
