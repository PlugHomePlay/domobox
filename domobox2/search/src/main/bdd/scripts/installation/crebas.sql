/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     21/04/2015 23:54:37                          */
/*==============================================================*/


/*==============================================================*/
/* Table: Capteur                                               */
/*==============================================================*/
create table Capteur (
   uid                  VARCHAR(30)          not null,
   Typ_uid              VARCHAR(30)          not null,
   Rol_uid              VARCHAR(30)          not null,
   Fab_uid              VARCHAR(30)          not null,
   sid                  VARCHAR(30)          null,
   nom                  VARCHAR(150)         null,
   constraint PK_CAPTEUR primary key (uid)
);

/*==============================================================*/
/* Table: Fabricant                                             */
/*==============================================================*/
create table Fabricant (
   uid                  VARCHAR(30)          not null,
   nom                  VARCHAR(150)         null,
   constraint PK_FABRICANT primary key (uid)
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role (
   uid                  VARCHAR(30)          not null,
   nom                  VARCHAR(150)         null,
   constraint PK_ROLE primary key (uid)
);

/*==============================================================*/
/* Table: TypeDeCapteur                                         */
/*==============================================================*/
create table TypeDeCapteur (
   uid                  VARCHAR(30)          not null,
   libelle              VARCHAR(50)          null,
   constraint PK_TYPEDECAPTEUR primary key (uid)
);

alter table Capteur
   add constraint FK_CAPTEUR_ASSOCIATI_FABRICAN foreign key (Fab_uid)
      references Fabricant (uid)
      on delete restrict on update restrict;

alter table Capteur
   add constraint FK_CAPTEUR_ASSOCIATI_TYPEDECA foreign key (Typ_uid)
      references TypeDeCapteur (uid)
      on delete restrict on update restrict;

alter table Capteur
   add constraint FK_CAPTEUR_CAPTEURRO_ROLE foreign key (Rol_uid)
      references Role (uid)
      on delete restrict on update restrict;

