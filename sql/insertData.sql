insert into location(locationid, lon, lat, title, description, address)
    VALUES (location_sequence.nextval,
            48.209310,
            9.032535,
            'Hochschule Albstadt-Sigmaringen Win-Gebaeude',
            'Das Win-Gebaeude der Hochschule Albstadt-Sigmaringen zeichnet sich aus durch ...',
            'Johannesstrasse 3');

insert into LOCATION VALUES (location_sequence.nextval,
            48.210358,
            9.027387,
            'Bahnhof Albstadt-Ebingen',
            'Der Bahnhof ...',
            'Poststraße 5');

insert into LOCATION VALUES (location_sequence.nextval,
            48.212098,
            9.026858,
            'Müller Drogeriemarkt',
            '',
            'Kirchengraben 38-42');
INSERT INTO LOCATION VALUES (location_sequence.nextval,
            48.212466,
            9.028437,
            'Star Kebap',
            '',
            'Untere Vorstadt 6');

commit;
select * from location;
