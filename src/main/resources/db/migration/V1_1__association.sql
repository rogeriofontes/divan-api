create table tb_association (
    id bigint generated by default as identity,
    about varchar(4000),
    active boolean not null,
    address varchar(255) not null,
    email varchar(45) not null,
    foundation_date timestamp(6) not null,
    name varchar(255) not null,
    phone varchar(15) not null,
    postal_code varchar(20) not null,
    status varchar(255) not null,
    create_by varchar(255) not null,
    created_date timestamp(6) not null,
    last_modified_by varchar(255),
    last_modified_date timestamp(6),
    primary key (id)
)