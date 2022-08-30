create sequence shaverma_schm.user_id_seq;

alter table shaverma_schm.User alter column id set default nextval('shaverma_schm.user_id_seq');

alter sequence shaverma_schm.user_id_seq owned by shaverma_schm."user".id;