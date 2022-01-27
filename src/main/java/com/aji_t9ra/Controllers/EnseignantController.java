package com.aji_t9ra.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aji_t9ra.DAO.EnseignantDAO;
import com.aji_t9ra.DAO.UserDAO;
import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.Etudiant;
import com.aji_t9ra.Models.User;

/**
 * Servlet implementation class EnseignantController
 */
@WebServlet("/Enseignant")
public class EnseignantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private UserDAO userDao = new UserDAO();
	private EnseignantDAO enseignantDao= new EnseignantDAO();
	
    public EnseignantController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
       doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
		 String op =request.getParameter("op");
		 if(op.equals("addenseignant")) {
			 try {
			   HttpSession session =request.getSession();
			   User newUser= (User)session.getAttribute("newUser");
			   int id =userDao.AddUser(newUser);
			   Enseignant newEnseignant = new Enseignant();
			   newEnseignant.setNiveau(request.getParameter("niveau"));
			   newEnseignant.setOrganisme(request.getParameter("organisme"));
			   newEnseignant.setDescription(request.getParameter("description"));
			   newEnseignant.setNom(newUser.getNom());
			   newEnseignant.setPrenom(newUser.getPrenom());
			   newEnseignant.setEmail(newUser.getEmail());
			   newEnseignant.setPassword(newUser.getPassword());
			   newEnseignant.setUsername(newUser.getUsername());
			   newEnseignant.setId(id);
			   enseignantDao.AddEnseignant(newEnseignant);
	           response.sendRedirect("login.jsp");
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 }
		 if(op.equals("newEnseignants"))
		 {

			 try {
				List<Enseignant> listNewEnseignants = enseignantDao.getNewEnseignants();
				request.setAttribute("listNewEnseignants", listNewEnseignants);
				RequestDispatcher dispatcher = request.getRequestDispatcher("newEnseignants.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(op.equals("profile"))
		 {

			 try {
				int id =Integer.parseInt(request.getParameter("id"));
				Enseignant ens = enseignantDao.getEnseignantByID(id);
				request.setAttribute("profile", "enseignant");
				request.setAttribute("enseignant", ens);
				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(op.equals("approver"))
		 {

			 try {
				int id =Integer.parseInt(request.getParameter("id"));
				enseignantDao.ApproverEnseignant(id);
				Enseignant ens = enseignantDao.getEnseignantByID(id);
				request.setAttribute("enseignant", ens);
				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(op.equals("desactiver"))
		 {

			 try {
				int id =Integer.parseInt(request.getParameter("id"));
				enseignantDao.desactiverEnseignant(id);
				Enseignant ens = enseignantDao.getEnseignantByID(id);
				request.setAttribute("enseignant", ens);
				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(op.equals("activer"))
		 {

			 try {
				int id =Integer.parseInt(request.getParameter("id"));
				enseignantDao.activerEnseignant(id);
				Enseignant ens = enseignantDao.getEnseignantByID(id);
				request.setAttribute("enseignant", ens);
				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(op.equals("supprimer"))
		 {

			 try {
				int id =Integer.parseInt(request.getParameter("id"));
				enseignantDao.supprimerEnseignant(id);
				response.sendRedirect("newEnseignants.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}

}
