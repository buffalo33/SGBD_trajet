drop table LIEU cascade constraints;
drop table ZONE cascade constraints;

-- =====================================
-- Table : LIEU
-- =====================================
create table LIEU
(
	NUMERO_LIEU	 NUMBER(3) 	primary key,
	ADRESSE_LIEU  	CHAR(100) 		,
	NUMERO_ZONE 	 NUMBER(3) 	not null
);


-- =====================================
-- Table : ZONE
-- =====================================
create table ZONE
(
	NUMERO_ZONE	 NUMBER(3) 	primary key,
	NOM_ZONE  	CHAR(50) 		
);

alter table LIEU
      add constraint fk_lieu foreign key (NUMERO_ZONE) references ZONE (NUMERO_ZONE);

alter table TRAJET
      add constraint fk_depart foreign key (NUMERO_LIEU_DEPART) references LIEU (NUMERO_LIEU);

alter table TRAJET
      add constraint fk_arrivee foreign key (NUMERO_LIEU_ARRIVE) references LIEU (NUMERO_LIEU);


