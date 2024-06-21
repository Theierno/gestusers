package forms;
import java.io.Console;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import javax.activation.MailcapCommandMap;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import beans.Utilisateur;
import dao.UtilisateurDao;


public class AddForm {
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_LOGIN  = "login";
	private static final String CHAMP_PASSWORD = "password";
	private static final String CHAMP_PASSWORD_BIS = "passwordBis";
	private static final String EMPTY_FIELD_ERROR_MESSAGE = "Vous devez renseigner ce champs";
	private static final String WRONG_PASSWORD_ERROR_MESSAGE = "Les mots de passe ne sont pas conformes"; 
	private HttpServletRequest request;
	private Map<String, String> erreurs;
	private boolean status;
	private String statusMessage;
	private Utilisateur utilisateur;

	public AddForm(HttpServletRequest request) {
		this.request = request;
		this.erreurs = new HashMap<String, String>();
	}
	

	public boolean ajouter()  {
		String nom = getPrarameter(CHAMP_NOM);
		String prenom = getPrarameter(CHAMP_PRENOM);
		String login = getPrarameter(CHAMP_LOGIN);
		String password = getPrarameter(CHAMP_PASSWORD);
		
		this.utilisateur = new Utilisateur(nom,prenom,login,password);
		validerChamps(CHAMP_NOM,CHAMP_PRENOM,CHAMP_LOGIN,CHAMP_PASSWORD,CHAMP_PASSWORD_BIS);
		validerPasswords();
		
		if(this.erreurs.isEmpty()) {
			this.status = true;
			this.statusMessage = "Ajoute effectue avec succes";
			UtilisateurDao.ajouter(utilisateur);
		}else {
//			System.out.println(this.erreurs);
			this.status = false;
			this.statusMessage = "echec de l'ajout";
		}
		return this.status;
	}
	private String getPrarameter(String parametre) {
		String valeur = this.request.getParameter(parametre);
		return (valeur == null||(valeur.trim()).isEmpty() )?null:valeur.trim();
					
	}
	
	private void validerChamps(String... parameters) {
		for(String parametre:parameters) {
			if(this.getPrarameter(parametre) == null) {
				this.erreurs.put(parametre,EMPTY_FIELD_ERROR_MESSAGE);
			}
		}
	}
	private void validerPasswords() {
		String password= this.getPrarameter(CHAMP_PASSWORD);
		String passwordBis = this.getPrarameter(CHAMP_PASSWORD_BIS);
		if(password != null && !password.equals(passwordBis)) {
			this.erreurs.put(CHAMP_PASSWORD,WRONG_PASSWORD_ERROR_MESSAGE);
			this.erreurs.put(CHAMP_PASSWORD_BIS,WRONG_PASSWORD_ERROR_MESSAGE);
		}
	}
	
	public boolean isStatus() {
		return this.status;
	}


	public HttpServletRequest getRequest() {
		return this.request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public Map<String, String> getErreurs() {
		return this.erreurs;
	}


	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}


	public String getStatusMessage() {
		return this.statusMessage;
	}


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}


	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
