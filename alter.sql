alter table INSCRIPTION
      add constraint fk1_inscription foreign key (NUMERO_TRAJET) references TRAJET (NUMERO_TRAJET);

alter table INSCRIPTION
      add constraint fk2_inscription foreign key (NUMERO_ETUDIANT) references ETUDIANT (NUMERO_ETUDIANT);

alter table COMMENTAIRE 
      add constraint fk1_commentaire foreign key (NUMERO_ETUDIANT_POSTEUR) references ETUDIANT (NUMERO_ETUDIANT);

alter table COMMENTAIRE
      add constraint fk2_commentaire foreign key (NUMERO_ETUDIANT_CONCERNE) references ETUDIANT (NUMERO_ETUDIANT);

alter table COMMENTAIRE
      add constraint fk3_commentaire foreign key (NUMERO_TRAJET) references TRAJET (NUMERO_TRAJET);

alter table MESSAGE
      add constraint fk1_message foreign key (NUMERO_ETUDIANT) references ETUDIANT (NUMERO_ETUDIANT);

alter table MESSAGE
      add constraint fk2_message foreign key (NUMERO_TRAJET) references TRAJET (NUMERO_TRAJET);

--alter table TRAJET
  --    add constraint fk2_inscription foreign key (NUMERO_EVENEMENT) references EVENEMENT (NUMERO_EVENEMENT);
