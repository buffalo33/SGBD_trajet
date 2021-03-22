-- =====================================
-- Nom de la base   : SOIREE
-- Nom de SGBD      : ORACLE Version 7.0
-- Date de creation : 04/11/2020
-- =====================================

drop table TRAJET cascade constraints;
drop table INSCRIPTION cascade constraints;
drop table EVENEMENT cascade constraints;

-- =====================================
-- Table : TRAJET
-- =====================================
create table TRAJET
(
	NUMERO_TRAJET   NUMBER(3) 	primary key,
	DATE_TRAJET	DATE 		,
	HEURE 		CHAR(20)	,
	NUMERO_EVENEMENT NUMBER(3)	not null,
	NUMERO_LIEU_DEPART NUMBER(3)	not null,
	NUMERO_LIEU_ARRIVEE NUMBER(3) 	not null
--	constraint pk_trajet primary key (NUMERO_TRAJET)
);

-- =====================================
-- Table : INSCRIPTION
-- =====================================
create table INSCRIPTION
(
	NUMERO_ETUDIANT		NUMBER(3)	not null,
	NUMERO_TRAJET		NUMBER(3)	not null
	--constraint pk_inscription primary key (NUMERO_TRAJET, NUMERO_ETUDIANT)
);

-- =====================================
-- Table : EVENEMENT 
-- =====================================
create table EVENEMENT
(
	NUMERO_EVENEMENT	NUMBER(3)	primary key,
	NOM_EVENEMENT		CHAR(30)	
--	constraint pk_evenement primary key (NUMERO_EVENEMENT)
);

alter table INSCRIPTION
      add constraint pk_inscription primary key (NUMERO_ETUDIANT, NUMERO_TRAJET);

alter table TRAJET
      add constraint pk_evenement foreign key (NUMERO_EVENEMENT) references EVENEMENT (NUMERO_EVENEMENT);
