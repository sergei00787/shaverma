create table if not exists shaverma_schm.Ingredient (
                                          id varchar(4) not null,
                                          name varchar(25) not null,
                                          type integer not null
);

alter table shaverma_schm.Ingredient
    add constraint Ingredient_pk
        primary key (id);

create table if not exists shaverma_schm.Shaverma (
                                        id bigint not null,
                                        name varchar(50) not null,
                                        createdAt timestamp not null);

alter table shaverma_schm.Shaverma
    add constraint Shaverma_pk
        primary key (id);

create table if not exists shaverma_schm.Shaverma_Ingredients (
                                                    shaverma bigint not null,
                                                    ingredient varchar(4) not null);

alter table shaverma_schm.Shaverma_Ingredients add foreign key (shaverma) references shaverma_schm.Shaverma(id);
alter table shaverma_schm.Shaverma_Ingredients add foreign key (ingredient) references shaverma_schm.Ingredient(id);

create table if not exists shaverma_schm.Shaverma_Order (
                                              id bigint not null,
                                              deliveryName varchar(50) not null,
                                              deliveryStreet varchar(50) not null,
                                              deliveryCity varchar(50) not null,
                                              deliveryState varchar(2) not null,
                                              deliveryZip varchar(10) not null,
                                              ccNumber varchar(16) not null,
                                              ccExpiration varchar(5) not null,
                                              ccCVV varchar(3) not null,
                                              placedAt timestamp not null
);

alter table shaverma_schm.Shaverma_Order
    add constraint Shaverma_Order_pk
        primary key (id);

create table if not exists shaverma_schm.Shaverma_Order_Shavermas (
                                                        shavermaOrder bigint not null,
                                                        shaverma bigint not null
);

alter table shaverma_schm.Shaverma_Order_Shavermas add foreign key (shavermaOrder) references shaverma_schm.Shaverma_Order(id);
alter table shaverma_schm.Shaverma_Order_Shavermas add foreign key (shaverma) references shaverma_schm.Shaverma(id);