insert into location(locationid, shapes, title, description, address)
    VALUES (location_sequence.nextval,
            SDO_GEOMETRY(2003, null, null, SDO_ELEM_INFO_ARRAY(1,1003, 3),
            SDO_ORDINATE_ARRAY(1,8,5,14)),
            'Hochschule Albstadt-Sigmaringen Win-Gebaeude',
            'Das Win-Gebaeude der Hochschule Albstadt-Sigmaringen zeichnet sich aus durch ...',
            'Johannesstrasse 3');
commit;
select * from location;

SELECT * from location l where SDO_CONTAINS(l.SHAPES, SDO_GEOMETRY(2001,null, SDO_POINT_TYPE(7,7, null), null, null)) = 'TRUE';

INSERT INTO cola_markets VALUES(
  0,
  'cola_x',
  SDO_GEOMETRY(
    2003,  -- zweidimensionales Polygon
    NULL,  -- sdo_srid
    NULL,  -- sdo_point
    SDO_ELEM_INFO_ARRAY(1,1003,3), -- 1003 = Polygon, 3 = Rechteck
    SDO_ORDINATE_ARRAY(1,8, 5,14) -- da "Rechteck" gewaehlt muessen nur 2 Punkte angegeben werden
          -- Links unten und rechts oben (als Cartesiche Koordinaten)
  )
);
