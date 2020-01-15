drop table location purge;
drop sequence location_sequence;
drop sequence comment_sequence;

create sequence location_sequence start with 1 increment by 1 nocache nocycle;
create sequence comment_sequence start with 1 increment by 1 nocache nocycle;
create table location(locationid number primary key not null,
                      lon double precision,
                      lat double precision,
                      title varchar2(50),
                      description varchar2(1000),
                      address varchar2(50));

create table comment(commentid number primary key not null,
                     locationid number not null,
                     commentary varchar2(200));