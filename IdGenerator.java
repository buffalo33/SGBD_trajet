import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;


public class IdGenerator{
    private static int idEtudiant;
    private static int idTrajet;
    private static int idEvenement;
    private static int idLieu;
    private static int idZone;

    IdGenerator(Statement stmt) throws SQLException, ClassNotFoundException, java.io.IOException {
	ResultSet rset = stmt.executeQuery("select max(NUMERO_ETUDIANT)"
					   +"from ETUDIANT");
	rset.next();
	idEtudiant = rset.getInt(1);
	
	rset = stmt.executeQuery("select max(NUMERO_TRAJET)"
				     +"from TRAJET");
	rset.next();    
	idTrajet = rset.getInt(1);
	
	rset = stmt.executeQuery("select max(NUMERO_EVENEMENT)"
				 +"from EVENEMENT");
	rset.next();
	idEvenement = rset.getInt(1);
	    
	rset = stmt.executeQuery("select max(NUMERO_LIEU)"
				     +"from LIEU");
	rset.next();
	idLieu = rset.getInt(1);
	    
	rset = stmt.executeQuery("select max(NUMERO_ZONE)"
				     +"from ZONE");
	rset.next();
	idZone = rset.getInt(1);
    }

    void debug(){
	System.out.println(idEtudiant + " " +  idTrajet + " " + idEvenement + " " + idLieu + " " + idZone);
    }
    
    static public int genIdEtudiant(){
	idEtudiant++;
	return idEtudiant;
    }
	    
    static public int genIdTrajet(){
	idTrajet++;
	return idTrajet;
    }

    static public int genIdEvenement(){
	idEvenement++;
	return idEvenement;
    }

    static public int genIdLieu(){
	idLieu++;
	return idLieu;

    }

    static public int genIdZone(){
	idZone++;
	return idZone;

    }

}
