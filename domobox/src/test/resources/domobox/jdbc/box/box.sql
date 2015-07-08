DROP TABLE IF EXISTS BOX;

CREATE TABLE BOX
(
    ID 			BIGINT		 			not null,
    NOM			VARCHAR(50)		UNIQUE 	not null,
    DESCRIPTION	VARCHAR(500)					,
    constraint PK_BOX primary key (ID)
);

--DROP SEQUENCE SEQ_GEN_SEQUENCE;
CREATE SEQUENCE SEQ_BOX START 1;