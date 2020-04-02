package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				Corso tempC = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(tempC);
				
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Data una matricola, ricevo i corsi che segue
	 */
	
	public List<Corso> getCorsiDaMatricola(int matricola) {

		final String sql = "SELECT *\r\n" + 
				"FROM corso\r\n" + 
				"WHERE codins IN (SELECT codins\r\n" + 
				"                 FROM iscrizione\r\n" + 
				"				     WHERE matricola = ?)";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println("Corsi della matricola "+ matricola +"\n"+codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				Corso tempC = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(tempC);
				
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	
	public boolean inscriviStudenteACorso(int matricola, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		
		
		final String sql = "INSERT INTO iscrizione\r\n" + 
				"(\r\n" + 
				"   matricola,\r\n" + 
				"   codins\r\n" + 
				")\r\n" + 
				"VALUES\r\n" + 
				"(\r\n" + 
				"   ?,\r\n" + 
				"   ?\r\n" + 
				")";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			st.setString(2, corso.getCodins());

			ResultSet rs = st.executeQuery();

			conn.close();
			
			return true;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}

}






