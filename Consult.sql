----Consultation
-- liste des trajets existant entre 2 zones donnees a une date donnees
select NUMERO_TRAJET
from TRAJET, LIEU, ZONE
where TRAJET.NUMERO_LIEU_DEPART = LIEU.NUMERO_LIEU
and LIEU.NUMERO_ZONE = ZONE.NUMERO_ZONE
and ZONE.NOM_ZONE = 'Victoire'
and TRAJET.DATE_TRAJET = '02-JAN-20'
intersect
select NUMERO_TRAJET
from TRAJET, LIEU, ZONE
where TRAJET.NUMERO_LIEU_ARRIVEE= LIEU.NUMERO_LIEU
and LIEU.NUMERO_ZONE = ZONE.NUMERO_ZONE
and ZONE.NOM_ZONE = 'Pessac-centre'
and TRAJET.DATE_TRAJET = '02-JAN-20'
;

-- Liste des trajets qui ne sont rataches a aucun evenement
select NUMERO_TRAJET
from  TRAJET
where TRAJET.NUMERO_EVENEMENT is null;

-- Liste des paires d'etudiant qui ont fait au moins un trajet ensemble

select E1.NOM_ETUDIANT, E1.PRENOM_ETUDIANT, E2.NOM_ETUDIANT, E2.PRENOM_ETUDIANT
from 
     (select I1.NUMERO_ETUDIANT as N_E1, I2.NUMERO_ETUDIANT as N_E2
     from INSCRIPTION I1 left outer join INSCRIPTION I2
     on I2.NUMERO_TRAJET = I1.NUMERO_TRAJET
     and I1.NUMERO_ETUDIANT != I2.NUMERO_ETUDIANT
     where I1.NUMERO_ETUDIANT is not null
     and I2.NUMERO_ETUDIANT is not null
     order by I1.NUMERO_ETUDIANT)
, ETUDIANT E1, ETUDIANT E2
where E1.NUMERO_ETUDIANT = N_E1
and E2.NUMERO_ETUDIANT = N_E2;
  


----Statistiques
--Liste des trajets, avec le nombre de participants
select TRAJET.NUMERO_TRAJET, count(ETUDIANT.NUMERO_ETUDIANT)
from ETUDIANT, INSCRIPTION, TRAJET
WHERE ETUDIANT.NUMERO_ETUDIANT = INSCRIPTION.NUMERO_ETUDIANT
and INSCRIPTION.NUMERO_TRAJET = TRAJET.NUMERO_TRAJET
group by TRAJET.NUMERO_TRAJET;

--Liste des zones geographiques avec le nombre de lieux par zone
select ZONE.NOM_ZONE, count(distinct LIEU.NUMERO_LIEU)
from LIEU, ZONE
where LIEU.NUMERO_ZONE = ZONE.NUMERO_ZONE
group by ZONE.NOM_ZONE;

-- Liste des etudiants, avec le nombre de trajets effectues et la moyenne de leurs evaluations
select ETUDIANT.NOM_ETUDIANT, ETUDIANT.PRENOM_ETUDIANT, NB_TRAJET, NOTE
from
	(select ETUDIANT.NUMERO_ETUDIANT as NUM_TAB, count(INSCRIPTION.NUMERO_TRAJET) as NB_TRAJET, avg(NOTE_COMMENTAIRE) as NOTE
	from ETUDIANT
	left outer join INSCRIPTION
	on ETUDIANT.NUMERO_ETUDIANT = INSCRIPTION.NUMERO_ETUDIANT
	left outer join COMMENTAIRE
	on ETUDIANT.NUMERO_ETUDIANT = COMMENTAIRE.NUMERO_ETUDIANT_CONCERNE
	group by ETUDIANT.NUMERO_ETUDIANT) Tab, ETUDIANT
where Tab.NUM_TAB = ETUDIANT.NUMERO_ETUDIANT
;


--Liste des zones geographiques, avec pour chaque zone, le lieu le plus utilise, soit comme arrivee soit comme depart

select NUMERO_LIEU, TAB_LIEU.NUMERO_ZONE FROM
       (select LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE, count(TRAJET.NUMERO_LIEU_ARRIVEE) as NB_TRAJ
       from TRAJET, LIEU	  
       where TRAJET.NUMERO_LIEU_ARRIVEE = LIEU.NUMERO_LIEU
       group by LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE) TAB_LIEU
       ,
      (select max(NB_TRAJ) as MAX_TRAJ, NUMERO_ZONE from
      	      (select LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE, count(TRAJET.NUMERO_LIEU_ARRIVEE) as NB_TRAJ	
      	      from TRAJET, LIEU	  
      	      where TRAJET.NUMERO_LIEU_ARRIVEE = LIEU.NUMERO_LIEU
      	      group by LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE)
      group by NUMERO_ZONE) TAB_MAX
where TAB_LIEU.NUMERO_ZONE = TAB_MAX.NUMERO_ZONE
and TAB_LIEU.NB_TRAJ = TAB_MAX.MAX_TRAJ;





select NUMERO_LIEU, TAB_LIEU.NUMERO_ZONE FROM
       (select LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE, count(TRAJET.NUMERO_LIEU_DEPART) as NB_TRAJ
       from TRAJET, LIEU	  
       where TRAJET.NUMERO_LIEU_DEPART = LIEU.NUMERO_LIEU
       group by LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE) TAB_LIEU
       ,
      (select max(NB_TRAJ) as MAX_TRAJ, NUMERO_ZONE from
      	      (select LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE, count(TRAJET.NUMERO_LIEU_DEPART) as NB_TRAJ	
      	      from TRAJET, LIEU	  
      	      where TRAJET.NUMERO_LIEU_DEPART = LIEU.NUMERO_LIEU
      	      group by LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE)
      group by NUMERO_ZONE) TAB_MAX
where TAB_LIEU.NUMERO_ZONE = TAB_MAX.NUMERO_ZONE
and TAB_LIEU.NB_TRAJ = TAB_MAX.MAX_TRAJ;
