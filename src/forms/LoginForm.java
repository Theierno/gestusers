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


public class LoginForm {
	private static final String CHAMP_LOGIN  = "login";
	private static final String CHAMP_PASSWORD = "password";
	private HttpServletRequest request;
	private Map<String, String> erreurs;
	private boolean status;
	private String statusMessage;
	private Utilisateur utilisateur;

	public LoginForm(HttpServletRequest request) {
		this.request = request;
		this.erreurs = new HashMap<String, String>();
	}
	

	public boolean login() {
		String login = getPrarameter(CHAMP_LOGIN);
		String password = getPrarameter(CHAMP_PASSWORD);
		try {
			this.utilisateur = UtilisateurDao.get(login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(this.utilisateur != null && this.utilisateur.getPassword().equals(password)) {
			this.status = true;
			this.statusMessage = "Authentification reussie";
		}else {
			this.utilisateur = new Utilisateur(login);
			this.status = false;
			this.statusMessage = "echec de l'authentification";
		}
		return this.status;
	}
	
	private String getPrarameter(String parametre) {
		String valeur = this.request.getParameter(parametre);
		return (valeur == null||(valeur.trim()).isEmpty() )?null:valeur.trim();
					
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
