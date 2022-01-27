package com.aji_t9ra.Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aji_t9ra.Models.User;
import com.aji_t9ra.DAO.LoginDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private LoginDAO loginDao = new LoginDAO();
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
          String email = request.getParameter("email");
          String password= request.getParameter("password");
          User user = loginDao.login(email, password);
          if(user!=null && user.isApproved()){
              HttpSession session = request.getSession();
              session.setAttribute("user", user);
              response.sendRedirect("index.jsp");
          }else{
              response.sendRedirect("login.jsp");
          }
	}

}
