delete from shaverma_schm.shaverma_order_shavermas;
delete from shaverma_schm.shaverma_ingredients;
delete from shaverma_schm.shaverma;
delete from shaverma_schm.shaverma_order;
delete from shaverma_schm.ingredient;

insert into shaverma_schm.ingredient (id, name, type) values ('KTCH', 'Ketchup', 0);
insert into shaverma_schm.ingredient (id, name, type) values ('MZCK', 'Mazik', 0);
insert into shaverma_schm.ingredient (id, name, type) values ('LVSH', 'Lavash', 1);
insert into shaverma_schm.ingredient (id, name, type) values ('BTNO', 'Batono', 1);
insert into shaverma_schm.ingredient (id, name, type) values ('CRRT', 'Carrot', 2);
insert into shaverma_schm.ingredient (id, name, type) values ('PTFR', 'Pottato Free', 2);

insert into shaverma_schm.user(id,username,password) values (1,'admin','cd455611f00f99e369f0fe9f9f6c9ef5a54071827bbb35665fa4374f3413a8822a98654baf6fd839');


commit;
