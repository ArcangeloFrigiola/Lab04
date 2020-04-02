/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLController {

	Model model = new Model();
	ObservableList<Corso> listaCorsi = FXCollections.observableArrayList(model.getTuttiCorsi());
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="boxCorsi"
    private ChoiceBox<Corso> boxCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader
    
    @FXML
    void doCercaCorsi(ActionEvent event) {

    	this.txtRisultato.clear();
    	int matricola = controllaMatricola(this.txtMatricola.getText());
		if (matricola > 0) {

			try {

				List<Corso> listaCorsi = new LinkedList<>(this.model.getCorsiDaMatricola(matricola));

				if (!listaCorsi.isEmpty()) {
					this.txtRisultato.appendText("Corsi seguiti dalla matricola " + matricola + ":\n");
					for (Corso c : listaCorsi) {
						this.txtRisultato.appendText(c.descriviCorso());
					}
				} else {
					this.txtRisultato.appendText("Errore, nessun corso trovato per la matricola " + matricola + "\n");
				}
			} catch (NullPointerException npe) {
				this.txtRisultato.appendText("Matricola non assegnata ad alcun corso!");
				return;
			}

    	}
    	
    	
    }

	@FXML
	void doCompletaAutomaticamente(ActionEvent event) {

		this.txtRisultato.clear();
		this.txtCognome.clear();
		this.txtNome.clear();

		int matricola = controllaMatricola(this.txtMatricola.getText());
		if (matricola > 0) {
			Studente temp = model.getNomeCognomeStudenteDaMatricola(matricola);
			if (temp == null) {
				this.txtRisultato.appendText("Matricola non presente nel Database");
				return;
			}
			this.txtCognome.setText(temp.getCognome());
			this.txtNome.setText(temp.getNome());
			return;
		}
		return;

	}
    
	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {

		String matricola = this.txtMatricola.getText();
		if (matricola.compareTo("")!=0) {

			int mtr =controllaMatricola(matricola);
			if(mtr>0) {
				
				if(model.getMatchMatricolaCorso(mtr, this.boxCorsi.getValue())) {
					
					this.txtRisultato.appendText("Studente già iscritto a questo corso");
				}
				else {
					this.txtRisultato.appendText("Studente non iscritto a questo corso");
				}
			}
			
			
		} else {
			try {
				List<Studente> listaStudenti = new ArrayList<>(
						model.getListaStudentiDaCorso(this.boxCorsi.getValue().getCodins()));

				this.txtRisultato.clear();
				for (Studente s : listaStudenti) {
					this.txtRisultato.appendText(s.toString());
				}
			} catch (NullPointerException npe) {
				this.txtRisultato.appendText("Scegliere un corso dal menù a tendina!");
			}
		}
	}

    @FXML
    void doIscrivi(ActionEvent event) {

    	this.txtRisultato.clear();
		int mtr = controllaMatricola(this.txtMatricola.getText());
		if (mtr > 0) {
			/**
			 * Controllo che la matricola esista
			 */
			if (this.model.getNomeCognomeStudenteDaMatricola(mtr) != null) {
				if (this.model.iscriviStudenteAlCorso(mtr, this.boxCorsi.getValue())) {
					
				this.txtRisultato.appendText("Studente con matricola " + mtr + " correttamente iscritto al corso");
				return;
				}else {
					this.txtRisultato.appendText("Studente già iscritto a questo corso!");
					return;
				}
			}
			else {
				
				
				this.txtRisultato.appendText("Matricola non presente nel database!");
				return;
				
			}
		} 

	}

    @FXML
    void doReset(ActionEvent event) {

    	this.txtRisultato.clear();
    	this.txtCognome.clear();
		this.txtNome.clear();
		this.txtMatricola.clear();
		
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

        listaCorsi.add(0, (new Corso("", 0, "", 0)));
        this.boxCorsi.setItems(listaCorsi);
        this.boxCorsi.converterProperty();
        
    }
    
    public int controllaMatricola(String matricola) {
    	
		try {
			int mtr = Integer.parseInt(matricola);
			if (mtr < 0) {

				this.txtRisultato.appendText("Formato matricola errato: inserire valori positivi");
				return -1;

			} else if (this.txtMatricola.getText().length() > 6) {

				this.txtRisultato.appendText("Formato matricola errato: inserire massimo 6 cifre");
				return -1;

			}
			
			return mtr;
		} catch (NumberFormatException nfe) {

			this.txtRisultato.appendText("Formato matricola errato: inserire solo valori numerici");
			return -1;
		}

	}
}
