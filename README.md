Sytème de gestion de base de données afin de gérer des trajets
====

Contexte
---

Le dépôt contient un système de gestion de base de données. Le but ici est de permettre à des étudiant de se mettre en relation afin de partager un trajet commun lors d'un retour de soirée.Il est donc défini plusieurs entités comme les Etudiants, les Trajets, les Evénements ainsi que des Messages pour communiquer. Le projet utilise Oracle, Java et SQLPlus. Le projet a été réalisé par Vincent Ridacker, Eloi Magon de la Villehuchet, Augustin Gauchet et Clément Préaut.

Note importante
---
Le projet est réalisé grâce un serveur Oracle privé d'un établissement pédagogique. Le projet n'est donc pas exécutable pour un tier avec les paramètres actuels de connexion. Il est nécessaire de posséder un serveur Oracle et de modifier en conséquence les paramètres de connexion (`Projet.java` l.277).

Fonctionnalités
----
Les requêtes de mises à jour prévues par l'interface sont :

* ajout d'un étudiant 
* inscription à un trajet
* si le trajet souhaité n'existe pas, création du trajet
* si un lieu du trajet n'existe pas, création du lieu
* ajout de message
* suppression d'un étudiant

Les requêtes de consultation qu'offre l'interface sont :

* Liste des trajets existants entre deux zones données à une date donnée.
* Liste des trajets qui ne sont rattachés à aucun événement.
* Liste des paires d’étudiants qui ont fait au moins un trajet ensemble.

Enfin les requêtes statistiques disponibles sont :

* La liste des trajets, avec le nombre de participants.
* La liste des zones géographiques, avec le nombre de lieux par zone.
* La liste des étudiants, avec le nombre de trajets effectués et la moyenne de leurs évaluations.
* La liste des zones géographiques, avec pour chaque zone, le lieu le plus utilisé, soit comme arrivée soit comme départ.

Instruction
---

`./interface.sh` : Lance l'interface dans le terminal et affiche les instructions à l'écran.

Pour les commandes de requêtes :

* `addstudent` : ajout d'un étudiant
* `inscription` : inscription d'un étudiant à un trajet
* `addmessage` : ajout d'un message
* `delstudent` : suppression d'un étudiant
*  `trajetzonedate` : Liste des trajets existants entre deux zones données à une date donnée. Pour cette requête paramétrée, l'invité de commande nous demande d'abord d'entrer les deux villes puis une date. Pour la date, le format à utilisé est : JJ\_MM\_YYYY.
* `trajet0evenement` : Liste des trajets qui ne sont rattachés à aucun événement.
* `paireetudiants` :  Liste des paires d’étudiants qui ont fait au moins un trajet ensemble.
* `nombreparticipants` : La liste des trajets, avec le nombre de participants.
* `nombrelieuxzone` : La liste des zones géographiques, avec le nombre de lieux par zone.
* `nombretrajetnote` : La liste des étudiants, avec le nombre de trajets effectués et la moyenne de leurs évaluations.
* `lieudepartplusutilise` : La liste des zones géographiques, avec pour chaque zone, le lieu le plus utilisé comme départ.
* `lieuarriveeplusutilise` : La liste des zones géographiques, avec pour chaque zone, le lieu le plus utilisé comme arrivée. 