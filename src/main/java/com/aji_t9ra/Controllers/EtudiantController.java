package com.aji_t9ra.Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aji_t9ra.DAO.EtudiantDAO;
import com.aji_t9ra.DAO.UserDAO;
import com.aji_t9ra.Models.Etudiant;
import com.aji_t9ra.Models.User;

/**
 * Servlet implementation class EtudiantController
 */
@WebServlet("/Etudiant")
public class EtudiantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private UserDAO userDao = new UserDAO();
	private EtudiantDAO etudiantDao=new EtudiantDAO();
    public EtudiantController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String op =request.getParameter("op");
		 if(op.equals("addetudiant")) {
			 try {
			   HttpSession session =request.getSession();
			   User newUser= (User)session.getAttribute("newUser");

			   int id =userDao.AddUser(newUser);
			   Etudiant newEtudiant = new Etudiant();
			   newEtudiant.setNiveau_scolaire(request.getParameter("niveau"));
			   newEtudiant.setEcole(request.getParameter("ecole"));
			   newEtudiant.setDescription(request.getParameter("description"));
			   newEtudiant.setNom(newUser.getNom());
			   newEtudiant.setPrenom(newUser.getPrenom());
			   newEtudiant.setEmail(newUser.getEmail());
			   newEtudiant.setPassword(newUser.getPassword());
			   newEtudiant.setUsername(newUser.getUsername());
			   newEtudiant.setId(id);
			   etudiantDao.AddEtudiant(newEtudiant);
	           response.sendRedirect("login.jsp");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 }
	}

}
