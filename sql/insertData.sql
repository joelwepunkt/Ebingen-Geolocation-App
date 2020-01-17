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

INSERT INTO NOTE VALUES (note_sequence.nextval,
            1,
            'Die Hochschule ist sehr schön');

INSERT INTO NOTE VALUES (note_sequence.nextval,
            1,
            'Die Hochschule ist ...');

INSERT INTO NOTE VALUES (note_sequence.nextval,
            1,
            'Die Hochschule ...');

INSERT INTO NOTE VALUES (note_sequence.nextval,
            2,
            'Bahnhof');

INSERT INTO NOTE VALUES (note_sequence.nextval,
            3,
            'Müller');

INSERT INTO NOTE VALUES (note_sequence.nextval,
            4,
            'Star Kebap');

commit;

select * from location;

select * from note;
