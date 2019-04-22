
create table brands(
    id   bigint(20) not null auto_increment,
    name varchar(255),
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table car_models(
    id       bigint(20) not null auto_increment,
    name     varchar(255),
    brand_id bigint(20),
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table roles(
    id   bigint(20) not null auto_increment,
    name varchar(255),
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table users(
    id       bigint(20) not null auto_increment,
    name     varchar(255),
    password varchar(255),
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table users_roles(
    user_id bigint(20) not null,
    role_id bigint(20) not null,
    primary key (user_id, role_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

alter table car_models
    add constraint car_models_brand_fk foreign key (brand_id) references brands (id);
alter table users_roles
    add constraint users_roles_role_fk foreign key (role_id) references roles (id);
alter table users_roles
    add constraint users_roles_user_fk foreign key (user_id) references users (id);