delete from ETUDIANT;
delete from MESSAGE;
delete from COMMENTAIRE;
delete from INSCRIPTION;
delete from ZONE;
delete from EVENEMENT;
delete from TRAJET;


commit;

insert into ZONE values (1, 'Victoire');
insert into ZONE values (2, 'Pessac-centre');
insert into ZONE Values (3, 'Talence Campus');

insert into LIEU values (1, '126 rue Jean Mace, Bordeaux', 1);
insert into LIEU values (2, '8 rue de la Libération, Pessac', 2);
insert into LIEU values (3, '15 avenue du Grand-Pré, Talence', 3);
insert into LIEU values (4, '12bis impasse Mérigny, Bordeaux', 1);

insert into EVENEMENT values (1, 'merguez party');
insert into EVENEMENT values (2, 'Rooftop afterwork');

insert into TRAJET values (1, '01-JAN-20', '23h00', 1, 1, 1);
insert into TRAJET values (2, '02-JAN-20', '00h00', 1, 1, 2); 
insert into TRAJET values (3, '01-JAN-20', '23h30', 1, 1, 3);
insert into TRAJET values (4, '03-MAR-20', '22h45', 2, 2, 1);
insert into TRAJET values (5, '03-MAR-20', '23h30', 2, 2, 3);
insert into TRAJET values (6, '04-MAR-20', '01h00', 2, 2, 4);

insert into ETUDIANT values (1, 'Balkany', 'Archibald', 0568546513, 'archibald.balkany@perret.fr');
insert into ETUDIANT values (2, 'Balkany', 'Madame', 0512234548, 'madame.balkany@perret.fr');
insert into ETUDIANT values (3, 'Morvan', 'Jeremy', 0123996789, 'morvan@perret.fr');
insert into ETUDIANT values (4, 'René', 'Roger', 051223564, 'roger.roger@perret.fr');
insert into ETUDIANT values (5, 'Ribery', 'Frank', 0512555548, 'frank@wanadoo.fr');
insert into ETUDIANT values (6, 'Poivre', 'Patrick', 0552234548, 'pat@perret.fr');
insert into ETUDIANT values (7, 'Pecresse', 'Valerie', 0452234548, 'robert.lecompte@perret.fr');
insert into ETUDIANT values (8, 'Dupont', 'Jean', 0512234548, 'jean.dt@yahoo.fr');
insert into ETUDIANT values (9, 'Dupond', 'Jean', 0512234548, 'jean.dd@yahoo.fr');


insert into INSCRIPTION values (1, 1);
insert into INSCRIPTION values (2, 1);
insert into INSCRIPTION values (8, 2);
insert into INSCRIPTION values (9, 2);
insert into INSCRIPTION values (5, 3);
insert into INSCRIPTION values (7, 3);
insert into INSCRIPTION values (1, 3);
insert into INSCRIPTION values (7, 4);
insert into INSCRIPTION values (6, 5);
insert into INSCRIPTION values (5, 5);
insert into INSCRIPTION values (4, 5);



insert into COMMENTAIRE values (1, 2, 1, 5, 'Tres bon trajet en compagnie de madame');
insert into COMMENTAIRE values (2, 1, 1, 5, 'Tres bon trajet en compagnie de monsieur');
insert into COMMENTAIRE values (6, 5, 5, 2, 'Mauvais chanteur');

insert into MESSAGE values (2, 1, 'T as oublié ton sac archi');
insert into MESSAGE values (8, 2, 'Maintenant je comprends pourquoi on appelle ca un moteur à explosion');
insert into MESSAGE values (9, 2, 'Je dirais même plus');
insert into MESSAGE values (7, 3, 'Salut patou');
insert into MESSAGE values (5, 3, 'Oh champs élysées');     
