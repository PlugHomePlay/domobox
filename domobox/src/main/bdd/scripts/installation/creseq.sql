-- Sequence: domobox.seq_box

DROP SEQUENCE domobox.seq_box;

CREATE SEQUENCE domobox.seq_box
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 21
  CACHE 1;
ALTER TABLE domobox.seq_box
  OWNER TO domobox;
  

CREATE SEQUENCE domobox.seq_cap
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 21
  CACHE 1;
ALTER TABLE domobox.seq_cap
  OWNER TO domobox;
  
CREATE SEQUENCE domobox.seq_rol
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 21
  CACHE 1;
ALTER TABLE domobox.seq_rol
  OWNER TO domobox;