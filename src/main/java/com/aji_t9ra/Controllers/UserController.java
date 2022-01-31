package com.aji_t9ra.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aji_t9ra.DAO.EnseignantDAO;
import com.aji_t9ra.DAO.EtudiantDAO;
import com.aji_t9ra.DAO.LoginDAO;
import com.aji_t9ra.DAO.MatiereDAO;
import com.aji_t9ra.DAO.UserDAO;
import com.aji_t9ra.Models.Etudiant;
import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/User")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDao=new UserDAO();
    private EnseignantDAO enseignantDao= new EnseignantDAO();
    private EtudiantDAO etudiantDao=new EtudiantDAO();
    private MatiereDAO matiereDao=new MatiereDAO();
    private LoginDAO loginDao = new LoginDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
            doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op= request.getParameter("op");
		if(op.equals("adduser")) {
			User newUser =new User();
			newUser.setNom(request.getParameter("nom"));
			newUser.setEmail(request.getParameter("email"));
			newUser.setPrenom(request.getParameter("prenom"));
			newUser.setPassword(request.getParameter("password"));
			newUser.setUsername(request.getParameter("username"));
			newUser.setRole(request.getParameter("role"));
			if(newUser.getRole().equals("enseignant"))
			{
				/*request.setAttribute("newUser", newUser);
				RequestDispatcher rd =request.getRequestDispatcher("Enseignant");
	           response.sendRedirect("inscriptionEnseignant.jsp");
				rd.forward(request, response);*/
				
				HttpSession session=request.getSession();
				session.setAttribute("newUser", newUser);
				response.sendRedirect("inscriptionEnseignant.jsp");
			}
			else{
			/*  request.setAttribute("newUser", newUser);
				RequestDispatcher rd =request.getRequestDispatcher("Etudiant");
	            response.sendRedirect("inscriptionEtudiant.jsp");
				rd.forward(request, response);*/
				HttpSession session=request.getSession();
				session.setAttribute("newUser", newUser);
				response.sendRedirect("inscriptionEtudiant.jsp");

			}
			//userDAO.AddUSer(newUser);
		}
		 if(op.equals("profile"))
		 {

			 try {
				int id =Integer.parseInt(request.getParameter("id"));
				if(loginDao.isEtudiant(id))
				{ 
					Etudiant etudiant = etudiantDao.getEtudiantByID(id);
					request= Counts(request);
					request.setAttribute("profile", "etudiant");
					request.setAttribute("etudiant", etudiant);
					RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
					dispatcher.forward(request, response);
				}
				else if(loginDao.isEnseignant(id)) {   
					Enseignant enseignant =enseignantDao.getEnseignantByID(id);
					request= Counts(request);
					request.setAttribute("profile", "enseignant");
					request.setAttribute("enseignant", enseignant);
					RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
					dispatcher.forward(request, response);		
				}else {
					User user = userDao.getUserById(id);
					request= Counts(request);
					request.setAttribute("profile", "admin");
					request.setAttribute("admin", user);
					RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
					dispatcher.forward(request, response);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		if(op.equals("ComptesDesactiver")) {
			
			try {
				List<Etudiant> listEtudiants = etudiantDao.getDescativeEtudiants();
				List<Enseignant> listEnseignants = enseignantDao.getDesactiveEnseignants();
				request=Counts(request);
 				request.setAttribute("listEtudiants", listEtudiants);
 				request.setAttribute("listEnseignants", listEnseignants);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Users.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public HttpServletRequest Counts(HttpServletRequest request) throws SQLException
	{
		
		List<String> categories=matiereDao.getCategories();
		List<String> niveaux=matiereDao.getNiveaux();
		request.setAttribute("niveaux", niveaux);
		request.setAttribute("categories", categories);
		int nbrEn = enseignantDao.nombreNosEnseignant();
		int nbrNewEn=enseignantDao.nombreNouveauEnseignant();
		int nbrDEns=enseignantDao.nombreDesacEnseignant();
		int nbrEtu=etudiantDao.nombreNosEtudiant();
		int nbrNewEt=etudiantDao.nombreNouveauEtudiant();
		int nbrDEtu= etudiantDao.nombreDesacEtudiant();
		int nbrDUsers=userDao.nombreDesacUser();
		request.setAttribute("nosEnseignant",nbrEn );
		request.setAttribute("newEnseignant",nbrNewEn );
		request.setAttribute("DEnseignant",nbrDEns );
		request.setAttribute("nosEtudiant",nbrEtu );
		request.setAttribute("newEtudiant",nbrNewEt );
		request.setAttribute("DEtudiant",nbrDEtu );
		request.setAttribute("DUsers", nbrDUsers);
		return request;
	}
	
}
