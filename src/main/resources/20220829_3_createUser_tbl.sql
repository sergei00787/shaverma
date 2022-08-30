create table if not exists shaverma_schm.User (
    id bigint not null,
    username varchar(50),
    password varchar
);

alter table shaverma_schm.User
    add constraint User_pk
        primary key (id);