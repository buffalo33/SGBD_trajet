import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.Date;

import java.util.Scanner;

import java.io.IOException;


public class Projet
{
    static IdGenerator idGen;
       
    static String completeString(String s, int size){
	while(s.length() < size){
	    s = s + " ";
	//  System.out.println(i);
	}
	return s;
    }

    static void afficheRes(ResultSet rset) throws java.sql.SQLException{
	ResultSetMetaData rsmd = rset.getMetaData();
	int nbCol = rsmd.getColumnCount();
	for (int i = 1; i <= nbCol; i++){
	    System.out.print(rsmd.getColumnName(i) + "|\t");
	}
	System.out.print("\n");
	while(rset.next()){
	    for (int i = 1; i <= nbCol; i++){
		if (i > 1){
		    System.out.print(" ");
		}
		String colValue = rset.getString(i);
		System.out.print(colValue + " " );
	    }
	    System.out.println(" ");
	}
    }	
    
    static void ajouterEtudiant(PreparedStatement pstmt, Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
	
	String nomEtudiant;
	String prenomEtudiant;
	int telephoneEtudiant;
	String mailEtudiant;
	
	Scanner scanner = new Scanner(System.in);
	System.out.println("nom de l'étudiant : ");
	nomEtudiant = scanner.nextLine();
	nomEtudiant = completeString(nomEtudiant, 20);
	
	
	System.out.println("prénom de l'étudiant : ");
	prenomEtudiant = scanner.nextLine();
	prenomEtudiant = completeString(prenomEtudiant, 20);
	    
	    
	System.out.println("Adresse email de l'étudiant : ");
	mailEtudiant = scanner.nextLine();
	mailEtudiant = completeString(mailEtudiant, 50);

	
	System.out.println("Numéro de télephone de l'étudiant : ");
	telephoneEtudiant = Integer.parseInt(scanner.nextLine());
		
	
	pstmt = conn.prepareStatement("select EMAIL_ETUDIANT"
				      +" from ETUDIANT"
				      +" where EMAIL_ETUDIANT = ?");
	pstmt.setString(1, mailEtudiant);
	
	//ResultSet rsetCheckMail = pstmt.executeQuery();
	int checkMail = pstmt.executeUpdate();
   
	if(checkMail != 0) {
	    System.out.println("email already used");
	    return;
	}

	int numeroEtudiant = IdGenerator.genIdEtudiant();

	pstmt = conn.prepareStatement("insert into ETUDIANT values (?, ?, ?, ?, ?)");

	pstmt.setInt(1, numeroEtudiant);
	pstmt.setString(2, nomEtudiant);
	pstmt.setString(3, prenomEtudiant);
	pstmt.setInt(4, telephoneEtudiant);
	pstmt.setString(5, mailEtudiant);
	int err = pstmt.executeUpdate();
	if( err == 1){
	    System.out.println("Etudiant ajouté");
	}else{
	    System.out.println("echec ajout de l'étudiant");
	}
    }

    static int inscription(Connection conn) throws java.sql.SQLException{
	Statement stmt;
	stmt = conn.createStatement();
	PreparedStatement pstmt;
	Scanner scanner = new Scanner(System.in);
	System.out.println("Adresse email de l'étudiant : ");
	String mailEtudiant = scanner.nextLine();
	//	completeString(mailEtudiant, 50);
	/*
	ResultSet rsetTrajets = stmt.executeQuery("select NUMERO_TRAJET, L1.ADRESSE_LIEU as LIEU_DEPART, L2.ADRESSE_LIEU as LIEU_ARRIVEE "
						   + "from TRAJET, LIEU L1, LIEU L2 "
						   + "where TRAJET.NUMERO_LIEU_DEPART = L1.NUMERO_LIEU "
						   + "or TRAJET.NUMERO_LIEU_ARRIVEE = L2.NUMERO_LIEU"
						   );
						   afficheRes(rsetTrajets);*/
	//scanner = new Scanner(System.in);



	ResultSet rset = stmt.executeQuery("select TRAJET.NUMERO_TRAJET, L_DEPART.ADRESSE_LIEU as LIEU_DEPART, L_ARRIVEE.ADRESSE_LIEU as LIEU_ARRIVEE "
					   +"from INSCRIPTION, TRAJET, LIEU L_DEPART, LIEU L_ARRIVEE "
					   +"where INSCRIPTION.NUMERO_TRAJET = TRAJET.NUMERO_TRAJET "
					   +"and L_DEPART.NUMERO_LIEU = TRAJET.NUMERO_LIEU_DEPART "
					   +"and L_ARRIVEE.NUMERO_LIEU = TRAJET.NUMERO_LIEU_ARRIVEE");
	afficheRes(rset);
	
	System.out.println("Renseignez ici le numéro du trajet souhaité, si aucun  trajet ne convient, taper 0 pour ajouter un nouveau trajet: ");
	String idTrajet = scanner.nextLine();
	int numeroTrajet = Integer.parseInt(idTrajet);
	if (idTrajet.equals("0")){
	    System.out.println("Renseignez ici la date du nouveau trajet (format JJ-MM-AAAA) : ");
	    String dateTrajet = scanner.nextLine();
	    System.out.println("Renseignez ici l'adresse du lieu de départ du nouveau trajet : ");
	    String lieuDepart = scanner.nextLine();
	    lieuDepart = completeString(lieuDepart, 100);
	    System.out.println("Renseignez ici le numéro de zone du lieu ajouté : ");
	    String numZoneDepart = scanner.nextLine();
	    int numeroLieuDepart = IdGenerator.genIdLieu();
	    System.out.println("Renseignez ici l'heure de départ : ");
	    String heureDepart = scanner.nextLine();
	    heureDepart = completeString(heureDepart, 20);
	    
	    System.out.println("Renseignez ici l'adresse du lieu d'arrivée du nouveau trajet : ");
	    String lieuArrivee = scanner.nextLine();
	    lieuArrivee = completeString(lieuArrivee, 100);
	    System.out.println("Renseignez ici le numéro de zone du lieu ajouté : ");
	    String numZoneArrivee = scanner.nextLine();
	    int numeroLieuArrivee = IdGenerator.genIdLieu();


	    
	    pstmt = conn.prepareStatement("insert into LIEU values (?, ?, ?)");
	    pstmt.setInt(1, numeroLieuDepart);
	    pstmt.setString(2, lieuDepart);
	    pstmt.setInt(3, Integer.parseInt(numZoneDepart));
	    int err1 = pstmt.executeUpdate();
	    System.out.println("Lieu de départ ajouté avec succès");
	    
	    pstmt = conn.prepareStatement("insert into LIEU values (?, ?, ?)");
	    pstmt.setInt(1, numeroLieuArrivee);
	    pstmt.setString(2, lieuArrivee);
	    pstmt.setInt(3, Integer.parseInt(numZoneArrivee));
	    int err2 = pstmt.executeUpdate();
	    System.out.println("Lieu d'arrivée ajouté avec succès");
	    
	    numeroTrajet = IdGenerator.genIdTrajet();
	    
	    pstmt = conn.prepareStatement("insert into TRAJET values (?, ?, ?, ?, ?, ?)");
	    pstmt.setInt(1, numeroTrajet);
	    pstmt.setDate(2, makeDate(dateTrajet));
	    pstmt.setString(3, heureDepart);
	    pstmt.setInt(4, 1);
	    pstmt.setInt(5, numeroLieuDepart);
	    pstmt.setInt(6, numeroLieuArrivee);
	    int err3 = pstmt.executeUpdate();
	    System.out.println("Trajet ajouté avec succès");
	}
	
	int numEtudiant = mailToNum(conn, mailEtudiant);
	mailEtudiant = completeString(mailEtudiant, 50);
	
	pstmt = conn.prepareStatement("insert into INSCRIPTION values (?, ?)");
	pstmt.setInt(1, numEtudiant);
	pstmt.setInt(2, numeroTrajet);
	int err4 = pstmt.executeUpdate();
	System.out.println("Inscription effectuée avec succès");
	return 0;
    }
    
    static int mailToNum(Connection conn, String email) throws java.sql.SQLException {
	PreparedStatement pstmt;
	int num;
	ResultSet rset;
	email = completeString(email, 50);
	
	pstmt = conn.prepareStatement("select NUMERO_ETUDIANT " +
				      "from ETUDIANT " +
				      "where EMAIL_ETUDIANT = ? ");

	pstmt.setString(1, email);
	rset = pstmt.executeQuery();
	rset.next();
	num = rset.getInt(1);
	return num;
    }
    
    static void ajouterMessage(Connection conn) throws java.sql.SQLException {
	PreparedStatement pstmt;
	String email;
	int numEtudiant; 
	int trajet;
	String message;
	ResultSet rset;
	
	Scanner scanner = new Scanner(System.in);
	System.out.println("entrez email : ");
	email = scanner.nextLine();
	numEtudiant = mailToNum(conn, email);

	//System.out.println("debug1 : num = "+numEtudiant);
	
	pstmt = conn.prepareStatement("select TRAJET.NUMERO_TRAJET, L_DEPART.ADRESSE_LIEU as LIEU_DEPART, L_ARRIVEE.ADRESSE_LIEU as LIEU_ARRIVEE "
				      +"from INSCRIPTION, TRAJET, LIEU L_DEPART, LIEU L_ARRIVEE "
				      +"where INSCRIPTION.NUMERO_TRAJET = TRAJET.NUMERO_TRAJET "
				      +"and INSCRIPTION.NUMERO_ETUDIANT = ? "
				      +"and L_DEPART.NUMERO_LIEU = TRAJET.NUMERO_LIEU_DEPART "
				      +"and L_ARRIVEE.NUMERO_LIEU = TRAJET.NUMERO_LIEU_ARRIVEE");
	
	pstmt.setInt(1, numEtudiant);
	rset = pstmt.executeQuery();
	afficheRes(rset);
	
	System.out.println("Selectionner le numero du trajet ou vous souhaitez envoyer un message: ");
	trajet = Integer.parseInt(scanner.nextLine());
	System.out.println("Message: ");
	message = scanner.nextLine();
	message = completeString(message, 166);
	
	pstmt = conn.prepareStatement("insert into Message values (?, ?, ?)");

	pstmt.setInt(1, numEtudiant);
	pstmt.setInt(2, trajet);
	pstmt.setString(3, message);
	int err = pstmt.executeUpdate();
	if(err != -1){
	    System.out.println("message ajouté");
	}else{
	    System.out.println("echec ajout message");
	}
    }

    static void rmEtudiant(Connection conn)throws java.sql.SQLException{
	PreparedStatement pstmt;
	Scanner scanner = new Scanner(System.in);
	
	System.out.println("Adresse email de l'étudiant : ");
	String mailEtudiant = scanner.nextLine();
	int num = mailToNum(conn, mailEtudiant);
	pstmt = conn.prepareStatement("delete from ETUDIANT where ETUDIANT.NUMERO_ETUDIANT = ?");

	pstmt.setInt(1, num);
	int err = pstmt.executeUpdate();
	if (err  != -1){
	    System.out.println("etudiant supprime");
	}else{
	    System.out.println("echec suppression de l'etudiant");
	}
    }

    
    public static void main(String[] args)
	throws SQLException, ClassNotFoundException, java.io.IOException {
	// Preparation de la connexion.
	//System.out.println(args[0]);
	//System.out.println(args[1]); // entrer \$testpwd\$
	OracleDataSource ods = new OracleDataSource();
	ods.setUser(args[0]);
	ods.setPassword(args[1]);
	// URL de connexion, on remarque que le pilote utilise est "thin".
	ods.setURL("jdbc:oracle:thin:@localhost:1521/oracle");

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;

	try {
	    conn = ods.getConnection();
	    stmt = conn.createStatement();
	    ResultSetMetaData meta;
	    Projet object = new Projet();
	    idGen = new IdGenerator(stmt);
	    for (int i = 0 ; i < Integer.parseInt(args[2]) ; i++) {
		//System.out.println(args[3 + i]);
		String key_word = args[3 + i];
		
		
		// Execution de la requete.
		
		if (key_word.equals("trajetzonedate")){
		    System.out.println("trajetzonedate");

		    String zone1 = new String(args[3 + i + 1]);
		    while (zone1.length() < 50)
			{
			    zone1 += " ";
			}

		    String zone2 = new String(args[3 + i + 2]);
		    while (zone2.length() < 50)
			{
			    zone2 += " ";
			}

		    Date date = makeDate(args[3 + i + 3]);
		    
		    pstmt = conn.prepareStatement("select NUMERO_TRAJET "
						  +"from TRAJET, LIEU, ZONE "
						  +"where TRAJET.NUMERO_LIEU_DEPART = LIEU.NUMERO_LIEU "
						  +"and LIEU.NUMERO_ZONE = ZONE.NUMERO_ZONE "
						  +"and ZONE.NOM_ZONE = ? "
						  +"and TRAJET.DATE_TRAJET = ? "
						  +"intersect "
						  +"select NUMERO_TRAJET "
						  +"from TRAJET, LIEU, ZONE "
						  +"where TRAJET.NUMERO_LIEU_ARRIVEE= LIEU.NUMERO_LIEU "
						  +"and LIEU.NUMERO_ZONE = ZONE.NUMERO_ZONE "
						  +"and ZONE.NOM_ZONE = ? "
						  +"and TRAJET.DATE_TRAJET = ?");

		    //meta  = rset1.getMetaData();
		    //System.out.println(meta.getColumnName(1));

		    pstmt.setString(1,zone1);
		    pstmt.setDate(2,date);
		    pstmt.setString(3,zone2);
		    pstmt.setDate(4,date);
		  ResultSet rset1 = pstmt.executeQuery();
		  
		  meta = rset1.getMetaData();
		  System.out.println(meta.getColumnName(1));

		  while (rset1.next()) {
		      // Affichage du resultat.
		      System.out.println(rset1.getInt(1));
		      }
		    i = i + 3;
		}		  

		else if (key_word.equals("trajet0evenement")){
		    System.out.println("trajet0evenement");
		  
		    ResultSet rset2 = stmt.executeQuery("select NUMERO_TRAJET "
							+"from TRAJET "
							+"where TRAJET.NUMERO_EVENEMENT is null");
		    meta  = rset2.getMetaData();
		    System.out.println(meta.getColumnName(1));
		    while (rset2.next()) {
			// Affichage du resultat.
			System.out.println("Le trajet numero " + rset2.getInt(1) + " n'a pas d'evenement");
		    }
		}	  
	      
		else if (key_word.equals("paireetudiants")){
		    System.out.println("paireetudiants");
		  
		    ResultSet rset3 = stmt.executeQuery("select E1.NOM_ETUDIANT, E1.PRENOM_ETUDIANT, E2.NOM_ETUDIANT, E2.PRENOM_ETUDIANT " + "from (select I1.NUMERO_ETUDIANT as N_E1, I2.NUMERO_ETUDIANT as N_E2 "
							+ "from INSCRIPTION I1 left outer join INSCRIPTION I2 "
							+ "on I2.NUMERO_TRAJET = I1.NUMERO_TRAJET "
							+"and I1.NUMERO_ETUDIANT != I2.NUMERO_ETUDIANT "
							+"where I1.NUMERO_ETUDIANT is not null "
							+"and I2.NUMERO_ETUDIANT is not null "
							+"order by I1.NUMERO_ETUDIANT) "
							+ ", ETUDIANT E1, ETUDIANT E2 "
							+ "where E1.NUMERO_ETUDIANT = N_E1 "
							+ "and E2.NUMERO_ETUDIANT = N_E2");
		    meta  = rset3.getMetaData();
		    System.out.println(meta.getColumnName(1) + Spaces(8) + meta.getColumnName(2) + Spaces(5) + meta.getColumnName(3) + Spaces(8) + meta.getColumnName(4));		    

		    while (rset3.next()) {
			// Affichage du resultat.
			System.out.println(rset3.getString(1) + rset3.getString(2) + rset3.getString(3) + rset3.getString(4));
			}
		}

		else
		    
		    if (key_word.equals("nombreparticipants")){
		    System.out.println("nombreparticipants");
		    ResultSet rset4 = stmt.executeQuery(
							"select TRAJET.NUMERO_TRAJET, count(ETUDIANT.NUMERO_ETUDIANT) "
							+"from ETUDIANT, INSCRIPTION, TRAJET "
							+"WHERE ETUDIANT.NUMERO_ETUDIANT = INSCRIPTION.NUMERO_ETUDIANT "
							+"and INSCRIPTION.NUMERO_TRAJET = TRAJET.NUMERO_TRAJET "
							+"group by TRAJET.NUMERO_TRAJET");
		    meta  = rset4.getMetaData();
		    System.out.println(meta.getColumnName(1) + " " + meta.getColumnName(2));
		    while (rset4.next()) {
			// Affichage du resultat			
			System.out.println(rset4.getInt(1) + Spaces(13) + rset4.getInt(2) );
		    }
			}
		    
		    else if (key_word.equals("nombrelieuxzone")){
		    System.out.println("nombrelieuxzone");
		  
		    ResultSet rset5 = stmt.executeQuery("select ZONE.NOM_ZONE, count(distinct LIEU.NUMERO_LIEU) "
							+"from LIEU, ZONE "
							+"where LIEU.NUMERO_ZONE = ZONE.NUMERO_ZONE "
							+"group by ZONE.NOM_ZONE");
		    meta  = rset5.getMetaData();
		    System.out.println(meta.getColumnName(1) + Spaces(42) + meta.getColumnName(2));


		    while (rset5.next()) {
			//Affichage du resultat.
			
			System.out.println(rset5.getString(1) + rset5.getInt(2));
		    }
		}
		  
		else if (key_word.equals("nombretrajetnote")){
		    System.out.println("nombretrajetnote");
		  
		    ResultSet rset6 = stmt.executeQuery("select ETUDIANT.NOM_ETUDIANT, ETUDIANT.PRENOM_ETUDIANT, NB_TRAJET, NOTE "
							+"from "
							+ "	(select ETUDIANT.NUMERO_ETUDIANT as NUM_TAB, count(INSCRIPTION.NUMERO_TRAJET) as NB_TRAJET, avg(NOTE_COMMENTAIRE) as NOTE " 
							+ "	from ETUDIANT "
							+ "	left outer join INSCRIPTION "
							+ "	on ETUDIANT.NUMERO_ETUDIANT = INSCRIPTION.NUMERO_ETUDIANT "
							+ "	left outer join COMMENTAIRE "
							+ "	on ETUDIANT.NUMERO_ETUDIANT = COMMENTAIRE.NUMERO_ETUDIANT_CONCERNE "
							+ "	group by ETUDIANT.NUMERO_ETUDIANT) Tab, ETUDIANT "
							+"where Tab.NUM_TAB = ETUDIANT.NUMERO_ETUDIANT");
		    //afficheRes(rset6);
		    meta  = rset6.getMetaData();
		    System.out.println(meta.getColumnName(1) + Spaces(8) + meta.getColumnName(2) + Spaces(5) + meta.getColumnName(3) + Spaces(1) + meta.getColumnName(4));

		    while (rset6.next()) {
			// Affichage du resultat.
			
			System.out.println(rset6.getString(1) + rset6.getString(2) + rset6.getInt(3) + Spaces(9) +rset6.getInt(4));
			}
		    //afficheRes(rset6); 
		}
		  
		else if (key_word.equals("lieuarriveeplusutilise")){//arrivées
		    System.out.println("lieuarriveeplusutilise");
		    ResultSet rset7 = stmt.executeQuery("select NUMERO_LIEU, TAB_LIEU.NUMERO_ZONE "
							+ " FROM "
							+ "       (select LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE, count(TRAJET.NUMERO_LIEU_ARRIVEE) as NB_TRAJ "
							+ "       from TRAJET, LIEU "
							+ "        where TRAJET.NUMERO_LIEU_ARRIVEE = LIEU.NUMERO_LIEU " 
							+ "       group by LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE) TAB_LIEU       , "
							+ "       (select max(NB_TRAJ) as MAX_TRAJ, NUMERO_ZONE from "
							+ "      	      (select LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE, count(TRAJET.NUMERO_LIEU_ARRIVEE) as NB_TRAJ "
							+ "      	      from TRAJET, LIEU	"
							+ "      	      where TRAJET.NUMERO_LIEU_ARRIVEE = LIEU.NUMERO_LIEU "
							+ "       	      group by LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE) "
							+ "       group by NUMERO_ZONE) TAB_MAX "
							+ "      where TAB_LIEU.NUMERO_ZONE = TAB_MAX.NUMERO_ZONE "
							+ "      and TAB_LIEU.NB_TRAJ = TAB_MAX.MAX_TRAJ");

		    meta  = rset7.getMetaData();
		    System.out.println(meta.getColumnName(1)+ " " + meta.getColumnName(2));
		    while (rset7.next()) {
			// Affichage du resultat.
			System.out.println(rset7.getString(1) + Spaces(11) +  rset7.getInt(2));
		    }
		}
		  
		else if (key_word.equals("lieudepartplusutilise")){//departs
		    System.out.println("lieudepartplusutilise");
		    ResultSet rset8 = stmt.executeQuery(

							"select NUMERO_LIEU, TAB_LIEU.NUMERO_ZONE "
							+ "FROM "
							+ "       (select LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE, count(TRAJET.NUMERO_LIEU_DEPART) as NB_TRAJ "
							+ "       from TRAJET, LIEU "
							+ "where TRAJET.NUMERO_LIEU_DEPART = LIEU.NUMERO_LIEU "
							+ "       group by LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE) TAB_LIEU "
							+ "       , "
							+ "      (select max(NB_TRAJ) as MAX_TRAJ, NUMERO_ZONE "
							+ " from"
							+ "      	      (select LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE, count(TRAJET.NUMERO_LIEU_DEPART) as NB_TRAJ "
							+ "      	      from TRAJET, LIEU	"
							+ "       	      where TRAJET.NUMERO_LIEU_DEPART = LIEU.NUMERO_LIEU "
							+ "      	      group by LIEU.NUMERO_LIEU, LIEU.NUMERO_ZONE) "
							+ "      group by NUMERO_ZONE) TAB_MAX "
							+ "where TAB_LIEU.NUMERO_ZONE = TAB_MAX.NUMERO_ZONE "
							+ "and TAB_LIEU.NB_TRAJ = TAB_MAX.MAX_TRAJ");

meta  = rset8.getMetaData();
		    System.out.println(meta.getColumnName(1)+ " " + meta.getColumnName(2));
     
		    while (rset8.next()) {
			// Affichage du resultat.
			System.out.println(rset8.getString(1) + Spaces(11) + rset8.getInt(2));
		    }
		      
		  
      
		}
		else if (key_word.equals("addstudent")){//departs
		    System.out.println("addstudent");
		    ajouterEtudiant(pstmt, conn);
		}
		else if (key_word.equals("inscription")){
		    System.out.println("inscription");
		    inscription(conn);
		}
		else if (key_word.equals("addmessage")){
		    System.out.println("addmessage");
		    ajouterMessage(conn);

		}
		else if (key_word.equals("delstudent")){
		    System.out.println("delstudent");
		    rmEtudiant(conn);
		}
	    }
	}

	catch(Exception e){
	    e.printStackTrace();
	}
	
	finally {
	    if (stmt != null) {
		stmt.close();
	    }
	    if (conn != null) {
		conn.close();
	    }
	}
    }      
  
		  
	static Date makeDate(String str)
	{
	    int day = Integer.parseInt(str.substring(0,2));
	    //System.out.println(day);
	    int month = Integer.parseInt(str.substring(3,5)) - 1;
	    //System.out.println(month);
	    int year = Integer.parseInt(str.substring(6,10)) - 1900;
	    //System.out.println(year);
	    Date res = new Date(year,month,day);
	    return res;
	}

    static String Spaces(int nb)
    {
	String str = "";
	for (int i = 0; i < nb ; i++)
	    {
		str += " ";
	    }
	return str;
    }

}
