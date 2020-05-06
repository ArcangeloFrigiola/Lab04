package it.polito.tdp.lab04.model;

import java.util.*;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO daoC;
	private StudenteDAO daoS;
	
	public Model() {
		daoC = new CorsoDAO();
		daoS = new StudenteDAO();
	}


	public List<Corso> getTuttiCorsi(){
		
		List<Corso> nomiCorsi = new LinkedList<>();
		
		for(Corso c: daoC.getTuttiICorsi()) {
			nomiCorsi.add(c);
		}
		
		return nomiCorsi;
	}
	
	public Studente getNomeCognomeStudenteDaMatricola(int matricola) {
		
		return daoS.getStudenteDaMatricola(matricola);
	}
	
	public List<Studente> getListaStudentiDaCorso(String codins){
		
		return this.daoS.getStudenteDaCorso(codins);
	}
	
	public List<Corso> getCorsiDaMatricola(int matricola){
		 return this.daoC.getCorsiDaMatricola(matricola);
	}
	
	public boolean getMatchMatricolaCorso(int matricola, Corso corso) {
		
		List<Studente> listaStudenti = new ArrayList<>(this.daoS.getStudenteDaCorso(corso.getCodins()));
		
		for(Studente s: listaStudenti) {
			if(s.equals(this.daoS.getStudenteDaMatricola(matricola))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean iscriviStudenteAlCorso(int matricola, Corso corso) {
		
		/**
		 * Controllo che lo studente non sia già iscritto
		 */
		if (getMatchMatricolaCorso(matricola, corso)) {
			return false;
		} else {
			this.daoC.inscriviStudenteACorso(matricola, corso);
			return true;
		}
	}
	
}
