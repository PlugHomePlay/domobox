DROP TABLE IF EXISTS CAPTEUR;

CREATE TABLE CAPTEUR
(
    ID 			BIGINT		 			not null,
    NOM			VARCHAR(50)		UNIQUE	not null,
    DESCRIPTION	VARCHAR(500)					,
    constraint PK_CAPTEUR primary key (ID)
);

