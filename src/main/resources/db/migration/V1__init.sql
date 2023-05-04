CREATE TABLE tb_user (
	id BIGSERIAL NOT NULL,
	name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	last_access timestamp NOT NULL DEFAULT CURRENT_DATE,
	create_by varchar(255) NOT NULL DEFAULT 'system_user',
    created_date timestamp NOT NULL DEFAULT CURRENT_DATE,
    last_modified_by varchar(255),
    last_modified_date timestamp NOT NULL DEFAULT CURRENT_DATE,
	primary key (id),
	constraint username_unique unique (email)
);

insert into tb_user (id, name, email, password, status) values (1, 'Rogério Fontes', 'fontestz@gmail.com', '$2a$10$4q2I1/BSLFOx64ji5oDz2uH.ZLOtQFi9N821ILDmjxO7wgt/gagnS', 'ATIVO');

CREATE TABLE tb_profile (
	id BIGSERIAL NOT NULL,
	role varchar(255),
	status varchar(255) NOT NULL,
	create_by varchar(255) NOT NULL DEFAULT 'system_user',
    created_date timestamp NOT NULL DEFAULT CURRENT_DATE,
    last_modified_by varchar(255),
    last_modified_date timestamp NOT NULL DEFAULT CURRENT_DATE,
	primary key (id)
);

insert into tb_profile (id, role, status) values (1, 'USER', 'ATIVO');
insert into tb_profile (id, role, status) values (2, 'ADMIN', 'ATIVO');

CREATE TABLE tb_user_profile (
    user_id int8 NOT NULL,
    profile_id int8 NOT NULL,
    CONSTRAINT user_profile_pkey PRIMARY KEY (user_id, profile_id)
);

insert into tb_user_profile (user_id, profile_id) values (1, 1);
insert into tb_user_profile (user_id, profile_id) values (1, 2);