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

insert into shaverma_schm.shaverma(id, name, created_at) values (1, 'supershavuha', '2022-09-06');
insert into shaverma_schm.shaverma(id, name, created_at) values (2, 'supershavuha2', '2022-09-06');
insert into shaverma_schm.shaverma(id, name, created_at) values (3, 'supershavuha3', '2022-09-06');

insert into shaverma_schm.SHAVERMA_INGREDIENTS(SHAVERMA_ID, INGREDIENTS_ID) values (1, 'KTCH');
insert into shaverma_schm.SHAVERMA_INGREDIENTS(SHAVERMA_ID, INGREDIENTS_ID) values (1, 'MZCK');
insert into shaverma_schm.SHAVERMA_INGREDIENTS(SHAVERMA_ID, INGREDIENTS_ID) values (2, 'LVSH');
insert into shaverma_schm.SHAVERMA_INGREDIENTS(SHAVERMA_ID, INGREDIENTS_ID) values (2, 'BTNO');
insert into shaverma_schm.SHAVERMA_INGREDIENTS(SHAVERMA_ID, INGREDIENTS_ID) values (3, 'CRRT');
insert into shaverma_schm.SHAVERMA_INGREDIENTS(SHAVERMA_ID, INGREDIENTS_ID) values (3, 'PTFR');

insert into SHAVERMA_SCHM.SHAVERMA_ORDER(ID, DELIVERY_NAME,DELIVERY_STREET, DELIVERY_CITY, DELIVERY_STATE,
                                         DELIVERY_ZIP, CC_NUMBER, CC_EXPIRATION, CC_CVV, PLACED_AT)
                                         values (1, 'DELIVERY1', 'street', 'city', 'CT', 156479,
                                                 2134324123, '02/23', 123, '2025-06-10');

insert into SHAVERMA_SCHM.SHAVERMA_ORDER_SHAVERMAS(SHAVERMAORDER, SHAVERMA) values (1, 1);
insert into SHAVERMA_SCHM.SHAVERMA_ORDER_SHAVERMAS(SHAVERMAORDER, SHAVERMA) values (1, 2);
insert into SHAVERMA_SCHM.SHAVERMA_ORDER_SHAVERMAS(SHAVERMAORDER, SHAVERMA) values (1, 3);


insert into shaverma_schm.user(id,username,password) values (1,'admin','cd455611f00f99e369f0fe9f9f6c9ef5a54071827bbb35665fa4374f3413a8822a98654baf6fd839');


commit;
