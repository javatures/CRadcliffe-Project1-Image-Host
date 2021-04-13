create table users {
    id integer primary key auto increment,
    userName text unique not null,
    password text not null,
    pLevel boolean not null,
    restrictions integer not null check (0 <= restrictions <= 3)
};

create table images {
    id integer pimary key auto increment,
    url text unique not null,
    description text,
    user integer references users(id),
    upvotes integer not null,
    downvotes integer not null
};

create table reports {
    id primary key auto increment,
    type boolean not null,
    reason integer not null,
    description text,
    source references users(id),
    hid references users(id),
    response text not null,
    appeal boolean

};

create table comments {
    id integer primary key auto increment,
    iid integer references images(id),
    uid integer references users(id),
    comment text not null
};