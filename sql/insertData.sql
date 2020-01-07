insert into location(locationid, shapes, title, description, address)
    VALUES (location_sequence.nextval,
            SDO_GEOMETRY(2003, null, null, SDO_ELEM_INFO_ARRAY(1,1003, 3),
            SDO_ORDINATE_ARRAY(1,8,5,14)),
            'Hochschule Albstadt-Sigmaringen Win-Gebaeude',
            'Das Win-Gebaeude der Hochschule Albstadt-Sigmaringen zeichnet sich aus durch ...',
            'Johannesstrasse 3');
commit;
select * from location;
