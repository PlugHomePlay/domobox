/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     17/05/2015 00:27:19                          */
/*==============================================================*/


drop index ACTION_PK;

drop table Action;

drop index BOX_PK;

drop table Box;

drop index ACTIONCAPTEUR_FK;

drop index EVENEMENTCAPTEUR_FK;

drop index ASSOCIATION4_FK;

drop index ASSOCIATION3_FK;

drop index ASSOCIATION2_FK;

drop index CAPTEURROLE_FK;

drop index CAPTEUR_PK;

drop table Capteur;

drop index EVENEMENT_PK;

drop table Evenement;

drop index FABRICANT_PK;

drop table Fabricant;

drop index ROLE_PK;

drop table Role;

drop index SCENARIO_PK;

drop table Scenario;

drop index TYPEDECAPTEUR_PK;

drop table TypeDeCapteur;

drop index UTILISATEUR_PK;

drop table Utilisateur;

drop index ASSOCIATION1_FK2;

drop index ASSOCIATION1_FK;

drop index ASSOCIATION1_PK;

drop table association1;

drop index SCENARIOACTION_FK2;

drop index SCENARIOACTION_FK;

drop index SCENARIOACTION_PK;

drop table scenarioAction;

drop domain DO_EMAIL;

drop domain DO_ID;

drop domain DO_IDENTIFIANT_CODE_LONG;

drop domain DO_LIBELLE_150;

drop domain DO_LIBELLE_50;

drop domain DO_PASSWORD;

drop domain DO_TEXTE;

/*==============================================================*/
/* Domain: DO_EMAIL                                             */
/*==============================================================*/
create domain DO_EMAIL as VARCHAR(200);

/*==============================================================*/
/* Domain: DO_ID                                                */
/*==============================================================*/
create domain DO_ID as INT8;

comment on domain DO_ID is
'LONG';

/*==============================================================*/
/* Domain: DO_IDENTIFIANT_CODE_LONG                             */
/*==============================================================*/
create domain DO_IDENTIFIANT_CODE_LONG as VARCHAR(36);

comment on domain DO_IDENTIFIANT_CODE_LONG is
'Code 30 caractères pour les identifiants longs.';

/*==============================================================*/
/* Domain: DO_LIBELLE_150                                       */
/*==============================================================*/
create domain DO_LIBELLE_150 as VARCHAR(150);

/*==============================================================*/
/* Domain: DO_LIBELLE_50                                        */
/*==============================================================*/
create domain DO_LIBELLE_50 as VARCHAR(50);

comment on domain DO_LIBELLE_50 is
'VARCHAR(50)';

/*==============================================================*/
/* Domain: DO_PASSWORD                                          */
/*==============================================================*/
create domain DO_PASSWORD as VARCHAR(32);

/*==============================================================*/
/* Domain: DO_TEXTE                                             */
/*==============================================================*/
create domain DO_TEXTE as TEXT;

/*==============================================================*/
/* Table: Action                                                */
/*==============================================================*/
create table Action (
   A_UID                DO_ID                not null,
   NOM                  DO_LIBELLE_150       null,
   constraint PK_ACTION primary key (A_UID)
);

/*==============================================================*/
/* Index: ACTION_PK                                             */
/*==============================================================*/
create unique index ACTION_PK on Action (
A_UID
);

/*==============================================================*/
/* Table: Box                                                   */
/*==============================================================*/
create table Box (
   B_UID                DO_ID                not null,
   NOM                  DO_LIBELLE_150       null,
   DESCRIPTION          DO_TEXTE             null,
   constraint PK_BOX primary key (B_UID)
);

/*==============================================================*/
/* Index: BOX_PK                                                */
/*==============================================================*/
create unique index BOX_PK on Box (
B_UID
);

/*==============================================================*/
/* Table: Capteur                                               */
/*==============================================================*/
create table Capteur (
   C_UID                DO_ID                not null,
   F_UID                DO_IDENTIFIANT_CODE_LONG not null,
   B_UID                DO_ID                null,
   E_UID                DO_ID                null,
   TC_UID               DO_ID                not null,
   R_UID                DO_ID                not null,
   A_UID                DO_ID                null,
   SID                  DO_IDENTIFIANT_CODE_LONG null,
   NOM                  DO_LIBELLE_150       null,
   DESCRIPTION          DO_TEXTE             null,
   constraint PK_CAPTEUR primary key (C_UID)
);

comment on table Capteur is
'Classe représentant un capteur';

comment on column Capteur.SID is
'Numéro de série du capteur';

/*==============================================================*/
/* Index: CAPTEUR_PK                                            */
/*==============================================================*/
create unique index CAPTEUR_PK on Capteur (
C_UID
);

/*==============================================================*/
/* Index: CAPTEURROLE_FK                                        */
/*==============================================================*/
create  index CAPTEURROLE_FK on Capteur (
R_UID
);

/*==============================================================*/
/* Index: ASSOCIATION2_FK                                       */
/*==============================================================*/
create  index ASSOCIATION2_FK on Capteur (
F_UID
);

/*==============================================================*/
/* Index: ASSOCIATION3_FK                                       */
/*==============================================================*/
create  index ASSOCIATION3_FK on Capteur (
TC_UID
);

/*==============================================================*/
/* Index: ASSOCIATION4_FK                                       */
/*==============================================================*/
create  index ASSOCIATION4_FK on Capteur (
B_UID
);

/*==============================================================*/
/* Index: EVENEMENTCAPTEUR_FK                                   */
/*==============================================================*/
create  index EVENEMENTCAPTEUR_FK on Capteur (
E_UID
);

/*==============================================================*/
/* Index: ACTIONCAPTEUR_FK                                      */
/*==============================================================*/
create  index ACTIONCAPTEUR_FK on Capteur (
A_UID
);

/*==============================================================*/
/* Table: Evenement                                             */
/*==============================================================*/
create table Evenement (
   E_UID                DO_ID                not null,
   NOM                  DO_LIBELLE_150       null,
   constraint PK_EVENEMENT primary key (E_UID)
);

/*==============================================================*/
/* Index: EVENEMENT_PK                                          */
/*==============================================================*/
create unique index EVENEMENT_PK on Evenement (
E_UID
);

/*==============================================================*/
/* Table: Fabricant                                             */
/*==============================================================*/
create table Fabricant (
   F_UID                DO_IDENTIFIANT_CODE_LONG not null,
   NOM                  DO_LIBELLE_150       null,
   constraint PK_FABRICANT primary key (F_UID)
);

/*==============================================================*/
/* Index: FABRICANT_PK                                          */
/*==============================================================*/
create unique index FABRICANT_PK on Fabricant (
F_UID
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role (
   R_UID                DO_ID                not null,
   NOM                  DO_LIBELLE_150       null,
   constraint PK_ROLE primary key (R_UID)
);

comment on table Role is
'Classe représentant un rôle particulier de capteur, qui peut être indiféremment
<ul>
<li>un actuateur,</li>
<li>une sonde,</li>
<li>...</li>
</ul>';

/*==============================================================*/
/* Index: ROLE_PK                                               */
/*==============================================================*/
create unique index ROLE_PK on Role (
R_UID
);

/*==============================================================*/
/* Table: Scenario                                              */
/*==============================================================*/
create table Scenario (
   S_UID                DO_ID                not null,
   NOM                  DO_LIBELLE_150       null,
   constraint PK_SCENARIO primary key (S_UID)
);

comment on table Scenario is
'Scenario d''action.
Par exemple:
La température de la pièce passe en dessous d''une certaine température allume le radiateur
En général, lorsqu''un élément déclencheur ou un ensemble d''éléments s''acitvent alors une action ou un ensemble d''action est misent en opération.';

/*==============================================================*/
/* Index: SCENARIO_PK                                           */
/*==============================================================*/
create unique index SCENARIO_PK on Scenario (
S_UID
);

/*==============================================================*/
/* Table: TypeDeCapteur                                         */
/*==============================================================*/
create table TypeDeCapteur (
   TC_UID               DO_ID                not null,
   LIBELLE              DO_LIBELLE_50        null,
   constraint PK_TYPEDECAPTEUR primary key (TC_UID)
);

/*==============================================================*/
/* Index: TYPEDECAPTEUR_PK                                      */
/*==============================================================*/
create unique index TYPEDECAPTEUR_PK on TypeDeCapteur (
TC_UID
);

/*==============================================================*/
/* Table: Utilisateur                                           */
/*==============================================================*/
create table Utilisateur (
   ID                   DO_ID                not null,
   identifiant          DO_LIBELLE_50        not null,
   password             DO_PASSWORD          not null,
   salt                 DO_SALT              not null,
   email                DO_EMAIL             not null,
   constraint PK_UTILISATEUR primary key (ID)
);

/*==============================================================*/
/* Index: UTILISATEUR_PK                                        */
/*==============================================================*/
create unique index UTILISATEUR_PK on Utilisateur (
ID
);

/*==============================================================*/
/* Table: association1                                          */
/*==============================================================*/
create table association1 (
   S_UID                DO_ID                not null,
   E_UID                DO_ID                not null,
   constraint PK_ASSOCIATION1 primary key (S_UID, E_UID)
);

/*==============================================================*/
/* Index: ASSOCIATION1_PK                                       */
/*==============================================================*/
create unique index ASSOCIATION1_PK on association1 (
S_UID,
E_UID
);

/*==============================================================*/
/* Index: ASSOCIATION1_FK                                       */
/*==============================================================*/
create  index ASSOCIATION1_FK on association1 (
S_UID
);

/*==============================================================*/
/* Index: ASSOCIATION1_FK2                                      */
/*==============================================================*/
create  index ASSOCIATION1_FK2 on association1 (
E_UID
);

/*==============================================================*/
/* Table: scenarioAction                                        */
/*==============================================================*/
create table scenarioAction (
   S_UID                DO_ID                not null,
   A_UID                DO_ID                not null,
   constraint PK_SCENARIOACTION primary key (S_UID, A_UID)
);

/*==============================================================*/
/* Index: SCENARIOACTION_PK                                     */
/*==============================================================*/
create unique index SCENARIOACTION_PK on scenarioAction (
S_UID,
A_UID
);

/*==============================================================*/
/* Index: SCENARIOACTION_FK                                     */
/*==============================================================*/
create  index SCENARIOACTION_FK on scenarioAction (
S_UID
);

/*==============================================================*/
/* Index: SCENARIOACTION_FK2                                    */
/*==============================================================*/
create  index SCENARIOACTION_FK2 on scenarioAction (
A_UID
);

alter table Capteur
   add constraint FK_CAPTEUR_ACTIONCAP_ACTION foreign key (A_UID)
      references Action (A_UID)
      on delete restrict on update restrict;

alter table Capteur
   add constraint FK_CAPTEUR_ASSOCIATI_FABRICAN foreign key (F_UID)
      references Fabricant (F_UID)
      on delete restrict on update restrict;

alter table Capteur
   add constraint FK_CAPTEUR_ASSOCIATI_TYPEDECA foreign key (TC_UID)
      references TypeDeCapteur (TC_UID)
      on delete restrict on update restrict;

alter table Capteur
   add constraint FK_CAPTEUR_ASSOCIATI_BOX foreign key (B_UID)
      references Box (B_UID)
      on delete restrict on update restrict;

alter table Capteur
   add constraint FK_CAPTEUR_CAPTEURRO_ROLE foreign key (R_UID)
      references Role (R_UID)
      on delete restrict on update restrict;

alter table Capteur
   add constraint FK_CAPTEUR_EVENEMENT_EVENEMEN foreign key (E_UID)
      references Evenement (E_UID)
      on delete restrict on update restrict;

alter table association1
   add constraint FK_ASSOCIAT_ASSOCIATI_EVENEMEN foreign key (E_UID)
      references Evenement (E_UID)
      on delete restrict on update restrict;

alter table association1
   add constraint FK_ASSOCIAT_ASSOCIATI_SCENARIO foreign key (S_UID)
      references Scenario (S_UID)
      on delete restrict on update restrict;

alter table scenarioAction
   add constraint FK_SCENARIO_SCENARIOA_ACTION foreign key (A_UID)
      references Action (A_UID)
      on delete restrict on update restrict;

alter table scenarioAction
   add constraint FK_SCENARIO_SCENARIOA_SCENARIO foreign key (S_UID)
      references Scenario (S_UID)
      on delete restrict on update restrict;

