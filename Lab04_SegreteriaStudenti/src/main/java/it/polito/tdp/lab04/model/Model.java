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
}
