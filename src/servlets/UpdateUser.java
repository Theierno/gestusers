package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import beans.Utilisateur;
import dao.UtilisateurDao;
import forms.UpdateForm;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/update")
public class UpdateUser extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String VUE_UPDATE_UTILISATEUR = "/WEB-INF/modifierUtilisateur.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String id = request.getParameter("id");
//		System.out.println("identifee"+id);
		if (id != null && id.matches("[0-9]+"))
		{
			Utilisateur utilisateur = null;
			try {
				utilisateur = UtilisateurDao.get(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (utilisateur != null)
			{
				request.setAttribute("utilisateur", utilisateur);
				getServletContext().getRequestDispatcher(VUE_UPDATE_UTILISATEUR).forward(request, response);
				return;
			}
		}

		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		String id = request.getParameter("id");
//		String nom = request.getParameter("nom");
//		String prenom = request.getParameter("prenom");
//		String login = request.getParameter("login");
//		String password = request.getParameter("password");
//
//		if (id != null && id.matches("[0-9]+"))
//		{
//			Utilisateur utilisateur = new Utilisateur(Integer.parseInt(id), nom, prenom, login, password);
//			UtilisateurDao.modifier(utilisateur);
//		}
		UpdateForm form = new UpdateForm(request);
		if(form.updateUser()) {
			response.sendRedirect(request.getContextPath());
			return;
		}else {
			request.setAttribute("status", form.isStatus());
			request.setAttribute("utilisateur", form.getUtilisateur());
			request.setAttribute("erreurs", form.getErreurs());
			request.setAttribute("statusMessage", form.getStatusMessage());
		}

		
		getServletContext().getRequestDispatcher(VUE_UPDATE_UTILISATEUR).forward(request, response);
	}

}
