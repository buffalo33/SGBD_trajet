--Ajoute un étudiant à la table n'appartenant pas préalablement à la table, l'identifiant n'est pas parametrable doit être automatiquement ajouté
insert into ETUDIANT values (numero_etudiant, nom_etudiant, prenom_etudiant, telephone_etudiant, mail_etudiant);

--Ajoute une zone, l'identifiant n'est pas parametrable doit être automatiquement ajouté
insert into ZONE values (numero_zone, nom_zone);

--Ajoute un evenement
insert into EVENEMENT values (numero_evenement, nom_evenement);

--ajoute un trajet, l'identifiant n'est pas parametrable doit être automatiquement ajouté.
--Si l'evenement n'existe pas, il faut le creer.
--Du point de vue utilisateur, on rentre des noms et pas des numero.
insert into LIEU values (numero_lieu_depart, adresse_depart, numero_zone_depart);
insert into LIEU values (numero_lieu_arrivee, adresse_arrivee, numero_zone_arrivee);
insert into TRAJET values (numero_trajet, date, heure_départ, numero_evenement, numero_lieu_depart, numero_lieu_arrivee);

--Ajoute une inscription.
insert into INSCRIPTION values (
