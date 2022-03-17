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
import com.aji_t9ra.DAO.MatiereDAO;
import com.aji_t9ra.DAO.UserDAO;
import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.Etudiant;
import com.aji_t9ra.Models.Matiere;
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
	private EtudiantDAO etudiantDao = new EtudiantDAO();
	private EnseignantDAO enseignantDao=new EnseignantDAO();
    private MatiereDAO matiereDao = new MatiereDAO();


	public EtudiantController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		doPost(request, response);
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
			   request.setCharacterEncoding("UTF-8");
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
		 
		 if(op.equals("newEtudiants"))
		 {

			 try {
				List<Etudiant> listNewEtudiants = etudiantDao.getNewEtudiants();
				request= Counts(request);
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("listEtudiants", listNewEtudiants);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Etudiants.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(op.equals("MatieresDispo"))
		 {
			 try {
					request.setCharacterEncoding("UTF-8");
					int id =Integer.parseInt(request.getParameter("id"));
					String categorie = request.getParameter("categorie");
					Etudiant e = etudiantDao.getEtudiantByID(id);
					List<Matiere> listMatieres=null;
		            if(categorie ==null) {
					listMatieres=matiereDao.getMatiereByNiveau(e.getNiveau_scolaire());
		            }else {
					listMatieres=matiereDao.getMatiereByNiveauAndCategorie(categorie,e.getNiveau_scolaire());	
		            }
		            request.setAttribute("MesMatieres", false);
					request.setAttribute("listMatieres", listMatieres);
					request = Counts(request);
					request.setAttribute("niveau",e.getNiveau_scolaire() );
					RequestDispatcher dispatcher = request.getRequestDispatcher("Matiere.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 if(op.equals("Etudiants"))
		 {

			 try {
				List<Etudiant> listNewEtudiants = etudiantDao.getEtudiants();
				request= Counts(request);
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("listEtudiants", listNewEtudiants);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Etudiants.jsp");
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
				Etudiant etd = etudiantDao.getEtudiantByID(id);
				request= Counts(request);
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("profile", "etudiant");
				request.setAttribute("etudiant", etd);
				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(op.equals("modifier"))
		 {

			 try {
				request.setCharacterEncoding("UTF-8");
				int id =Integer.parseInt(request.getParameter("id"));
				String description = (String)request.getParameter("description");
				etudiantDao.UpdateDescriptionEtudiant(id,description);
				Etudiant etudiant = etudiantDao.getEtudiantByID(id);
				request= Counts(request);
				request.setAttribute("profile", "etudiant");
				request.setAttribute("etudiant", etudiant);
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
				etudiantDao.ApproverEtudiant(id);
				Etudiant etd = etudiantDao.getEtudiantByID(id);
				request.setCharacterEncoding("UTF-8");
				request= Counts(request);
				request.setAttribute("etudiant", etd);
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
				etudiantDao.desactiverEtudiant(id);
				Etudiant etd = etudiantDao.getEtudiantByID(id);
				request.setCharacterEncoding("UTF-8");
				request= Counts(request);
				request.setAttribute("etudiant", etd);
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
				etudiantDao.activerEtudiant(id);
				Etudiant etd = etudiantDao.getEtudiantByID(id);
				request= Counts(request);
				request.setAttribute("etudiant", etd);
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
				etudiantDao.desactiverEtudiant(id);
				response.sendRedirect("newEtudiants.jsp");
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
