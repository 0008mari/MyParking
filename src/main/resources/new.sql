--
-- alter table reviews
-- drop
-- foreign key FKkr1abrb20vsgokvmiqkvy0ty4
--
-- alter table reviews
-- drop
-- foreign key FKsdlcf7wf8l1k0m00gik0m6b1m

drop table if exists parkings;

drop table if exists reviews;

drop table if exists user;

create table parkings (
                          parking_id bigint not null auto_increment,
                          parking_add_rates double precision,
                          parking_add_time_rate double precision,
                          parking_address varchar(500),
                          parking_capacity double precision,
                          parking_code varchar(100),
                          parking_name varchar(500),
                          parking_operation_rule_name varchar(20),
                          parking_pay_name varchar(20),
                          parking_rates double precision,
                          parking_tel varchar(20),
                          parking_time_rate double precision,
                          parking_type_name varchar(20),
                          parking_weekday_begin_time varchar(255),
                          parking_weekday_end_time varchar(255),
                          primary key (parking_id)
) engine=InnoDB;

create table reviews (
                         review_id bigint not null auto_increment,
                         created_date datetime(6),
                         modified_date datetime(6),
                         eval_costefficient varchar(255),
                         eval_parkinglevel varchar(255),
                         eval_revisit varchar(255),
                         eval_space varchar(255),
                         eval_staff varchar(255),
                         star_score integer not null,
                         parking_id bigint,
                         user_id bigint,
                         primary key (review_id)
) engine=InnoDB;

create table user (
                      user_id bigint not null auto_increment,
                      created_date datetime(6),
                      modified_date datetime(6),
                      email varchar(255) not null,
                      password varchar(255),
                      picture varchar(255),
                      role varchar(255) not null,
                      username varchar(255) not null,
                      primary key (user_id)
) engine=InnoDB;

alter table reviews
    add constraint FKkr1abrb20vsgokvmiqkvy0ty4
        foreign key (parking_id)
            references parkings (parking_id);

alter table reviews
    add constraint FKsdlcf7wf8l1k0m00gik0m6b1m
        foreign key (user_id)
            references user (user_id);