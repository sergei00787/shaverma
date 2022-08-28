delete from Shaverma_Order_Shavermas;
delete from Shaverma_Ingredients;
delete from Shaverma;
delete from Shaverma_Order;
delete from Ingredient;

insert into Ingredient (id, name, type) values ('KTCH', 'Ketchup', 0);
insert into Ingredient (id, name, type) values ('MZCK', 'Mazik', 0);
insert into Ingredient (id, name, type) values ('LVSH', 'Lavash', 1);
insert into Ingredient (id, name, type) values ('BTNO', 'Batono', 1);
insert into Ingredient (id, name, type) values ('CRRT', 'Carrot', 2);
insert into Ingredient (id, name, type) values ('PTFR', 'Pottato Free', 2);

commit;
