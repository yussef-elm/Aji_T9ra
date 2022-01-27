package com.aji_t9ra.Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aji_t9ra.DAO.UserDAO;
import com.aji_t9ra.Models.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/User")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO=new UserDAO();

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
		
	}

}
