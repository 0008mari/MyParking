-- alter table reviews
-- drop
-- foreign key FKjlb9ea856uatbq1py9kqigxli
--
-- alter table reviews
-- drop
-- foreign key FKsdlcf7wf8l1k0m00gik0m6b1m

drop table if exists parking;
drop table if exists reviews;
drop table if exists user;

create table parking (
                         parking_id bigint not null auto_increment,
                         addr varchar(255),
                         add_rates double precision not null,
                         add_time_rate double precision not null,
                         capacity double precision not null,
                         operation_rule_nm varchar(255),
                         parking_code varchar(255),
                         parking_name varchar(255),
                         parking_type_nm varchar(255),
                         pay_nm varchar(255),
                         rates double precision not null,
                         tel varchar(255),
                         time_rate double precision not null,
                         weekday_begin_time varchar(255),
                         weekday_end_time varchar(255),
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
    add constraint FKjlb9ea856uatbq1py9kqigxli
        foreign key (parking_id)
            references parking (parking_id);

alter table reviews
    add constraint FKsdlcf7wf8l1k0m00gik0m6b1m
        foreign key (user_id)
            references user (user_id);