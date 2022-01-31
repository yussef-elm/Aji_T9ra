package com.aji_t9ra.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aji_t9ra.Models.User;
import com.aji_t9ra.DAO.EnseignantDAO;
import com.aji_t9ra.DAO.EtudiantDAO;
import com.aji_t9ra.DAO.LoginDAO;
import com.aji_t9ra.DAO.MatiereDAO;
import com.aji_t9ra.DAO.UserDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private LoginDAO loginDao = new LoginDAO();
    private EnseignantDAO enseignantDao =new EnseignantDAO();
    private EtudiantDAO etudiantDao=new EtudiantDAO();
    private UserDAO userDao=new UserDAO();
    private MatiereDAO matiereDao = new MatiereDAO();
    public LoginController() {
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
        try { 
		String email = request.getParameter("email");
          String password= request.getParameter("password");
          User user = loginDao.login(email, password);
          if(user!=null && user.isApproved() && user.isActive()){
              HttpSession session = request.getSession();
              session=Counts(session);
              session.setAttribute("user", user);
              response.sendRedirect("index.jsp");
          }else{
              response.sendRedirect("login.jsp");
          }
        }catch (SQLException e) {
        	e.printStackTrace();
        	// TODO: handle exception
		}
	}

	public HttpSession Counts(HttpSession session) throws SQLException
	{
		List<String> categories=matiereDao.getCategories();
		List<String> niveaux=matiereDao.getNiveaux();
		session.setAttribute("niveaux", niveaux);
		session.setAttribute("categories", categories);
		int nbrEn = enseignantDao.nombreNosEnseignant();
		int nbrNewEn=enseignantDao.nombreNouveauEnseignant();
		int nbrDEns=enseignantDao.nombreDesacEnseignant();
		int nbrEtu=etudiantDao.nombreNosEtudiant();
		int nbrNewEt=etudiantDao.nombreNouveauEtudiant();
		int nbrDEtu= etudiantDao.nombreDesacEtudiant();
		int nbrDUsers=userDao.nombreDesacUser();
		session.setAttribute("nosEnseignant",nbrEn );
		session.setAttribute("newEnseignant",nbrNewEn );
		session.setAttribute("DEnseignant",nbrDEns );
		session.setAttribute("nosEtudiant",nbrEtu );
		session.setAttribute("newEtudiant",nbrNewEt );
		session.setAttribute("DEtudiant",nbrDEtu );
		session.setAttribute("DUsers", nbrDUsers);
		return session;
	}
	
	
}
