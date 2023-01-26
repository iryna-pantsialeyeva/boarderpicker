create table categories
(
    id   int auto_increment
    primary key,
    name varchar(255) null
);

create table producers
(
    id   int auto_increment
    primary key,
    name varchar(255) null,
    active bit
);

create table games
(
    id           int auto_increment
    primary key,
    description  varchar(255) null,
    name     varchar(255) null,
    picpath      varchar(255) null,
    producers_id int          null,
    foreign key (producers_id) references producers (id)
);

create table games_categories
(
    games_id      int not null,
    categories_id int not null,
    primary key (games_id, categories_id),
    foreign key (games_id) references games (id),
    foreign key (categories_id) references categories (id)
);

create table roles
(
    id   int auto_increment
    primary key,
    role varchar(255) null
);

create table users
(
    id       int auto_increment
    primary key,
    email    varchar(255) null,
    password varchar(255) null,
    username varchar(255) null,
    active bit
);

create table sales
(
    id       int auto_increment
        primary key,
    price    double not null,
    games_id int    null,
    users_id int    null,
    foreign key (users_id) references users (id)
            on delete cascade,

    foreign key (games_id) references games (id)
            on delete cascade
);

create table users_roles
(
    users_id int not null,
    roles_id int not null,
    primary key (users_id, roles_id),
    foreign key (roles_id) references roles (id),
    foreign key (users_id) references users (id)
);











