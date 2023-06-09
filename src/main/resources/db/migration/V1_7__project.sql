create table tb_project (
    id bigint generated by default as identity,
    create_by varchar(255) not null,
    created_date timestamp(6) not null,
    last_modified_by varchar(255),
    last_modified_date timestamp(6),
    status varchar(255) not null,
    about varchar(4000),
    address varchar(255) not null,
    description varchar(5000),
    email varchar(45) not null,
    end_date timestamp(6) not null,
    name varchar(255) not null,
    online boolean not null,
    repeat boolean not null,
    short_description varchar(1000) not null,
    start_date timestamp(6) not null,
    url varchar(255),
    association_id bigint not null,
    primary key (id)
)

--alter table
  --  if exists tb_project
--add
  --  constraint FKb6gypll00vj0a77onvnk55hgr foreign key (association_id) references tb_association