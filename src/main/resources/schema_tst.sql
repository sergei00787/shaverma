/*create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
    );

create table if not exists Shaverma (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null);

create table if not exists Shaverma_Ingredients (
    shaverma bigint not null,
    ingredient varchar(4) not null);

alter table Shaverma_Ingredients add foreign key (shaverma) references Shaverma(id);
alter table Shaverma_Ingredients add foreign key (ingredient) references Ingredient(id);

create table if not exists Shaverma_Order (
    id identity,
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

create table if not exists Shaverma_Order_Shavermas (
    shavermaOrder bigint not null,
    shaverma bigint not null
);

alter table Shaverma_Order_Shavermas add foreign key (shavermaOrder) references Shaverma_Order(id);
alter table Shaverma_Order_Shavermas add foreign key (shaverma) references Shaverma(id);
  */