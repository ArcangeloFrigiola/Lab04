package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	
	/*
	 * Data la matricola, restituisco lo stdudete
	 */
	
	public Studente getStudenteDaMatricola(int matricola){
		
		final String sql = "SELECT * FROM studente WHERE matricola = ?";

		Studente studenteTemp = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int mtr = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				studenteTemp = new Studente(mtr, cognome, nome, cds);
				
			}

			conn.close();
			
			
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		return studenteTemp;
	}
	

	/*
	 * Dato un corso, restituisce i rispettivi studenti
	 */
	public List<Studente> getStudenteDaCorso(String codins){
		
		final String sql = "SELECT * FROM studente WHERE matricola IN ( SELECT matricola FROM iscrizione WHERE codins = ?)";

		ArrayList<Studente> listaStudenti = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				listaStudenti.add(new Studente(matricola, cognome, nome, cds));
				
			}

			conn.close();
			
			
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		return listaStudenti;
	}
}
