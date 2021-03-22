-- =====================================
-- Nom de la base   : SOIREE
-- Nom de SGBD      : ORACLE Version 7.0
-- Date de creation : 04/11/2020
-- =====================================


drop table ETUDIANT cascade constraints;
drop table COMMENTAIRE cascade constraints;
drop table MESSAGE cascade constraints;
-- =====================================
-- Table : ETUDIANT
-- =====================================


create table ETUDIANT
(
	NUMERO_ETUDIANT		NUMBER(3)	primary key,
	NOM_ETUDIANT		CHAR(20)	not null,
	PRENOM_ETUDIANT		CHAR(20)		,
	TELEPHONE_ETUDIANT	NUMBER(10)	not null,
	EMAIL_ETUDIANT		char(50)		
--	constraints pk_etudiant primary key(NUMERO_ETUDIANT)
);

create table COMMENTAIRE
(
	NUMERO_ETUDIANT_POSTEUR		NUMBER(3)	not null,
	NUMERO_ETUDIANT_CONCERNE	NUMBER(3)	not null,
	NUMERO_TRAJET			NUMBER(3)	not null,
	NOTE_COMMENTAIRE		NUMBER(1)	not null,
	TEXTE_COMMENTAIRE		CHAR(166)		
);

create table MESSAGE
(
	NUMERO_ETUDIANT		NUMBER(3)	not null,
	NUMERO_TRAJET		NUMBER(3)	not null,
	TEXTE_MESSAGE		CHAR(166)	
);

alter table COMMENTAIRE
      add constraint pk_commentaire primary key (NUMERO_ETUDIANT_POSTEUR, NUMERO_ETUDIANT_CONCERNE, NUMERO_TRAJET);

alter table MESSAGE
      add constraint pk_message primary key (NUMERO_ETUDIANT, NUMERO_TRAJET);

create trigger DEL_ETUDIANT
before delete on ETUDIANT
for each row
begin
	delete from MESSAGE
	where MESSAGE.NUMERO_ETUDIANT = old.NUMERO_ETUDIANT
	delete from COMMENTAIRE
	where COMMENTAIRE.NUMERO_ETUDIANT_POSTEUR = old.NUMERO_ETUDIANT
	or COMMENTAIRE.NUMERO_ETUDIANT_CONCERNE = old.NUMERO_ETUDIANT
	delete from INSCRIPTION
	where INSCRIPTION.NUMERO_ETUDIANT = old.NUMERO_ETUDIANT
end;
/


