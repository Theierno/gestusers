package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class UpdateForm {
	private static final String CHAMP_ID = "id";
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

	
	
	public UpdateForm(HttpServletRequest request) {
		this.request = request;
		this.erreurs = new HashMap<String, String>();
	}
	
	public boolean updateUser() {
		int id = Integer.valueOf(this.getParameter(CHAMP_ID));
		String nom = this.getParameter(CHAMP_NOM);
		String prenom = this.getParameter(CHAMP_PRENOM);
		String login = this.getParameter(CHAMP_LOGIN);
		String password = this.getParameter(CHAMP_PASSWORD);
		this.utilisateur = new Utilisateur(id,nom,prenom,login,password);
		this.validerChamps(CHAMP_NOM,CHAMP_PRENOM,CHAMP_LOGIN,CHAMP_PASSWORD);
		this.validerPasswords();
		if(this.erreurs.isEmpty()) {
			UtilisateurDao.modifier(this.utilisateur);
			this.status = true;
			this.statusMessage = "Mis a jour reussie";
		}else {
			this.status = false;
			this.statusMessage = "Echec de la mis a jour";
		}
		return status;
	}
	
	
	
	private String getParameter(String parametre) {
		String valeur = this.request.getParameter(parametre);
		return (valeur == null||(valeur.trim()).isEmpty() )?null:valeur.trim();
	}
	
	private void validerChamps(String... parameters) {
		for(String parametre:parameters) {
			if(this.getParameter(parametre) == null) {
				this.erreurs.put(parametre,EMPTY_FIELD_ERROR_MESSAGE);
			}
		}
	
	}
	
	private void validerPasswords() {
		String password= this.getParameter(CHAMP_PASSWORD);
		String passwordBis = this.getParameter(CHAMP_PASSWORD_BIS);
		if(password != null && !password.equals(passwordBis)) {
			this.erreurs.put(CHAMP_PASSWORD,WRONG_PASSWORD_ERROR_MESSAGE);
			this.erreurs.put(CHAMP_PASSWORD_BIS,WRONG_PASSWORD_ERROR_MESSAGE);
		}
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	
	
	

}
