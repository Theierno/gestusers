package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forms.LoginForm;

/**
 * Servlet implementation class Authentification
 */
@WebServlet("/auth")
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_AUTH_UTILISATEUR = "/WEB-INF/loginPage.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(VUE_AUTH_UTILISATEUR).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		LoginForm form = new LoginForm(request);
		if(form.login() ) {
//			System.out.println("logged");
			session.setAttribute("isConnected", "connected");
			response.sendRedirect("list");
			return;
		}else {
			request.setAttribute("utilisateur", form.getUtilisateur());
			request.setAttribute("status", form.isStatus());
			request.setAttribute("statusMessage", form.getStatusMessage());
			session.setAttribute("isConnected", "connected");
		}
		getServletContext().getRequestDispatcher(VUE_AUTH_UTILISATEUR).forward(request, response);

//		doGet(request, response);
	}

}
