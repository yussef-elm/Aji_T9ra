package com.aji_t9ra.Controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.aji_t9ra.DAO.EtudiantDAO;
import com.aji_t9ra.DAO.LoginDAO;
import com.aji_t9ra.DAO.MatiereDAO;
import com.aji_t9ra.DAO.MeetingDAO;
import com.aji_t9ra.DAO.UserDAO;
import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.Etudiant;
import com.aji_t9ra.Models.Matiere;
import com.aji_t9ra.Models.User;
import java.sql.PreparedStatement;
import com.aji_t9ra.Models.*;
/**
 * Servlet implementation class EnseignantController
 */
@WebServlet("/meeting")
public class MeetingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDao=new UserDAO();
    private EnseignantDAO enseignantDao= new EnseignantDAO();
    private EtudiantDAO etudiantDao=new EtudiantDAO();
    private MatiereDAO matiereDao=new MatiereDAO();
    private LoginDAO loginDao = new LoginDAO();
    private MeetingDAO meetingDao = new MeetingDAO(); 
    /**
     * @see HttpServlet#HttpServlet()
     */

	
    public MeetingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 try {
			   HttpSession session =request.getSession();
			   User newUser= (User)session.getAttribute("newUser");
			   request.setCharacterEncoding("UTF-8");
			  List<Meeting> listMeetings = meetingDao.getAllMeetings(1);
				request.setAttribute("listMeetings", listMeetings);
				RequestDispatcher dispatcher = request.getRequestDispatcher("meeting.jsp");
				dispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
				
		 
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			 try {
			   HttpSession session =request.getSession();
			   User newUser= (User)session.getAttribute("newUser");
			   request.setCharacterEncoding("UTF-8");
			  meetingDao.addMeeting(newUser.getId(),Integer.parseInt(request.getParameter("meeting_id")),request.getParameter("link"));
			  List<Meeting> listMeetings = meetingDao.getAllMeetings(newUser.getId());
				request.setAttribute("listMeetings", listMeetings);
				RequestDispatcher dispatcher = request.getRequestDispatcher("meeting.jsp");
				dispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	
	public HttpServletRequest Counts(HttpServletRequest request) throws SQLException
	{

		int nbrDUsers=userDao.nombreDesacUser();
		request.setAttribute("DUsers", nbrDUsers);
		return request;
	}
	
	
}
