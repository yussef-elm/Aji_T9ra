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

import com.aji_t9ra.DAO.EnseignantDAO;
import com.aji_t9ra.DAO.EtudiantDAO;
import com.aji_t9ra.DAO.MatiereDAO;
import com.aji_t9ra.DAO.UserDAO;
import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.Matiere;

/**
 * Servlet implementation class MatiereController
 */
@WebServlet("/Matiere")
public class MatiereController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 * 
	 */
	private MatiereDAO matiereDao = new MatiereDAO();
	private EnseignantDAO enseignantDao = new EnseignantDAO();
	private EtudiantDAO etudiantDao = new EtudiantDAO();
	private UserDAO userDao = new UserDAO();

	public MatiereController() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String niveau = request.getParameter("niveau");
		String matiere = request.getParameter("matiere");
		String op = request.getParameter("op");
		String categorie = request.getParameter("categorie");
		String etat = request.getParameter("etat");
		

		if (op != null) {
			if (op.equals("MatiereUnavailable")) {
				try {
					Matiere m = matiereDao.getMatiereById(Integer.parseInt(request.getParameter("id")));
					matiereDao.MatiereIndisponible(m.getId());
					if (m.getNiveau().equals("Universitaire")) {
						List<Matiere> listMatieres = matiereDao.getMatiereByNiveauAndCategorie(m.getCategorie(),
								m.getNiveau());
						request.setAttribute("listMatieres", listMatieres);
					} else {
						List<Matiere> listMatieres = matiereDao.getMatiereByNiveau(m.getNiveau());
						request.setAttribute("listMatieres", listMatieres);
					}
					request = Counts(request);
					request.setAttribute("niveau", m.getNiveau());
					request.setAttribute("categorie", m.getCategorie());
					RequestDispatcher dispatcher = request.getRequestDispatcher("Matiere.jsp");
					dispatcher.forward(request, response);

				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (op.equals("MatiereAvailable")) {
				try {
					Matiere m = matiereDao.getMatiereById(Integer.parseInt(request.getParameter("id")));
					matiereDao.MatiereDisponible(m.getId());
					if (m.getNiveau().equals("Universitaire")) {
						List<Matiere> listMatieres = matiereDao.getMatiereByNiveauAndCategorie(m.getCategorie(),
								m.getNiveau());
						request.setAttribute("listMatieres", listMatieres);
					} else {
						List<Matiere> listMatieres = matiereDao.getMatiereByNiveau(m.getNiveau());
						request.setAttribute("listMatieres", listMatieres);
					}
					request = Counts(request);
					request.setAttribute("niveau", m.getNiveau());
					request.setAttribute("categorie", m.getCategorie());
					RequestDispatcher dispatcher = request.getRequestDispatcher("Matiere.jsp");
					dispatcher.forward(request, response);

				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (op.equals("ajouterMatiere")) {
				try {
					Matiere newMatiere = new Matiere();
					newMatiere.setNom((String) request.getParameter("Matiere"));
					newMatiere.setDescription((String) request.getParameter("description"));
					newMatiere.setCategorie((String) request.getParameter("categorie"));
					newMatiere.setNiveau((String) request.getParameter("niveau"));
					matiereDao.AddMatiere(newMatiere);
					List<Matiere> listMatieres = matiereDao.getMatiereByNiveauAndCategorie(categorie, niveau);
					request.setAttribute("listMatieres", listMatieres);
					request = Counts(request);
					request.setAttribute("niveau", newMatiere.getNiveau());
					request.setAttribute("categorie", newMatiere.getCategorie());
					RequestDispatcher dispatcher = request.getRequestDispatcher("Matiere.jsp");
					dispatcher.forward(request, response);

				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (op.equals("modifierMatiere")) {
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					Matiere Matiere = new Matiere();
					Matiere.setId(Integer.parseInt(request.getParameter("id")));
					Matiere.setNom((String) request.getParameter("Matiere"));
					Matiere.setDescription((String) request.getParameter("description"));
					Matiere.setCategorie((String) request.getParameter("categorie"));
					Matiere.setNiveau((String) request.getParameter("niveau"));
					matiereDao.UpdateMatiere(Matiere);
					List<Matiere> listMatieres = matiereDao.getMatiereByNiveauAndCategorie(categorie, niveau);
					request.setAttribute("listMatieres", listMatieres);
					request = Counts(request);
					request.setAttribute("niveau", Matiere.getNiveau());
					request.setAttribute("categorie", Matiere.getCategorie());
					RequestDispatcher dispatcher = request.getRequestDispatcher("Matiere.jsp");
					dispatcher.forward(request, response);

				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (op.equals("Ajouter")) {

				try {
					int idens = Integer.parseInt(request.getParameter("enseignant"));
					Matiere m = matiereDao.getMatiereByNom(matiere);
					enseignantDao.AjouterEnseignantAmatiere(idens, m.getId());
					List<Enseignant> listEnseignants = enseignantDao.getEnseignantsByMatiere(matiere);
					List<Enseignant> listEnseignantPossible = enseignantDao.getEnseignantsPossible(niveau, m.getId());
					request = Counts(request);
					request.setAttribute("listEnseignants", listEnseignants);
					request.setAttribute("listEnseignantPossible", listEnseignantPossible);
					request.setAttribute("matiere", matiere);
					request.setAttribute("niveau", niveau);
					request.setAttribute("Op", "matiere");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Enseignants.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (op.equals("Enlever")) {

				try {
					int idens = Integer.parseInt(request.getParameter("enseignant"));
					Matiere m = matiereDao.getMatiereByNom(matiere);
					enseignantDao.SupprimerEnseignantDeMatiere(idens, m.getId());
					List<Enseignant> listEnseignants = enseignantDao.getEnseignantsByMatiere(matiere);
					List<Enseignant> listEnseignantPossible = enseignantDao.getEnseignantsPossible(niveau, m.getId());

					request = Counts(request);
					request.setAttribute("listEnseignants", listEnseignants);
					request.setAttribute("listEnseignantPossible", listEnseignantPossible);
					request.setAttribute("matiere", matiere);
					request.setAttribute("niveau", niveau);
					request.setAttribute("Op", "matiere");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Enseignants.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			if (matiere == null) {

				try {

					if (categorie == null || categorie.equals("null")) {
						if (etat == null) {
							List<Matiere> listMatieres = matiereDao.getMatiereByNiveau(niveau);
							request.setAttribute("listMatieres", listMatieres);
						} else {
							List<Matiere> listMatieres = matiereDao.getUnavailableMatiereByNiveau(niveau);
							request.setAttribute("listMatieres", listMatieres);
						}

					} else {
						if (etat == null) {
							List<Matiere> listMatieres = matiereDao.getMatiereByNiveauAndCategorie(categorie, niveau);
							request.setAttribute("listMatieres", listMatieres);
						} else {
							List<Matiere> listMatieres = matiereDao.getMatiereUnavailableByNiveauAndCategorie(categorie,
									niveau);
							request.setAttribute("listMatieres", listMatieres);
						}

					}
					request = Counts(request);
					request.setAttribute("niveau", niveau);
					request.setAttribute("categorie", categorie);
					RequestDispatcher dispatcher = request.getRequestDispatcher("Matiere.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				
				try {
					List<Enseignant> listEnseignants = enseignantDao.getEnseignantsByMatiere(matiere);
					Matiere m = matiereDao.getMatiereByNom(matiere);
					List<Enseignant> listEnseignantPossible = enseignantDao.getEnseignantsPossible(niveau, m.getId());
					
					request = Counts(request);
					request.setAttribute("listEnseignants", listEnseignants);
					request.setAttribute("listEnseignantPossible", listEnseignantPossible);
					request.setAttribute("matiere", matiere);
					request.setAttribute("niveau", niveau);
					request.setAttribute("Op", "matiere");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Enseignants.jsp");
					dispatcher.forward(request, response);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public HttpServletRequest Counts(HttpServletRequest request) throws SQLException {
		List<String> categories = matiereDao.getCategories();
		List<String> niveaux = matiereDao.getNiveaux();
		request.setAttribute("niveaux", niveaux);
		request.setAttribute("categories", categories);
		int nbrEn = enseignantDao.nombreNosEnseignant();
		int nbrNewEn = enseignantDao.nombreNouveauEnseignant();
		int nbrDEns = enseignantDao.nombreDesacEnseignant();
		int nbrEtu = etudiantDao.nombreNosEtudiant();
		int nbrNewEt = etudiantDao.nombreNouveauEtudiant();
		int nbrDEtu = etudiantDao.nombreDesacEtudiant();
		int nbrDUsers = userDao.nombreDesacUser();
		request.setAttribute("nosEnseignant", nbrEn);
		request.setAttribute("newEnseignant", nbrNewEn);
		request.setAttribute("DEnseignant", nbrDEns);
		request.setAttribute("nosEtudiant", nbrEtu);
		request.setAttribute("newEtudiant", nbrNewEt);
		request.setAttribute("DEtudiant", nbrDEtu);
		request.setAttribute("DUsers", nbrDUsers);
		return request;
	}
}
