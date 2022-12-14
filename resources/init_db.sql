create table train
(
    id   serial       not null primary key,
    name varchar(255) not null unique
);

create table station
(
    id   serial       not null primary key,
    name varchar(255) not null unique
);

create table route
(
    id                   serial       not null primary key,
    name                 varchar(255) not null unique,
    train_id             bigint       not null,
    departure_station_id bigint       not null,
    arrival_station_id   bigint       not null,
    constraint route_train
        foreign key (train_id)
            references train (id) on delete cascade on update cascade,
    constraint route_departure_station
        foreign key (departure_station_id)
            references station (id) on delete cascade on update cascade,
    constraint route_arrival_station
        foreign key (arrival_station_id)
            references station (id) on delete cascade on update cascade
);

create table waypoint
(
    id         serial not null primary key,
    route_id   bigint not null,
    station_id bigint not null,
    departs_at timestamp,
    arrives_at timestamp,
    constraint waypoint_route
        foreign key (route_id)
            references route (id) on delete cascade on update cascade,
    constraint waypoint_station
        foreign key (station_id)
            references station (id) on delete cascade on update cascade
);
