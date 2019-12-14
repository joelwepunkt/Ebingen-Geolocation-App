drop table location purge;
drop sequence location_sequence;

create sequence location_sequence start with 1 increment by 1 nocache nocycle;
create table location(locationid number primary key not null,
                      shapes SDO_GEOMETRY,
                      title varchar2(50),
                      description varchar2(1000),
                      address varchar2(50));